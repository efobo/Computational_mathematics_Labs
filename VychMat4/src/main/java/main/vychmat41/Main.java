package main.vychmat41;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
//энтероколит

public class Main extends javafx.application.Application {
    private Double lowerBound;
    private Double upperBound;
    private Properties props;
    private Function experimentalFunction;
    private static Function[] cubicSplinesInterpolationFunctions;
    private static Double[] experimentalXPoints;
    private Double[] yPoints;
    private CubicSplinesInterpolation cubicSplinesInterpolation;

    @Override
    public void init() throws Exception {
        try (InputStream data = new FileInputStream("C:/Labs/VychMat4.1/src/main/java/data.properties")) {
            this.props = new Properties();
            this.props.load(data);
            /* Create an experimental function */
            this.experimentalFunction = new Function(props.getProperty("experimental.function"));

            /* Parse a string to double array */
            this.experimentalXPoints = Array.parseDoubleArray(props.getProperty("experimental.points"));
            this.yPoints = getValuesYFromX(experimentalXPoints);
            //this.interpolationPoints = Array.parseDoubleArray(props.getProperty("interpolation.points"));
            /* Calculate graph borders */
            this.lowerBound = Array.minOf(experimentalXPoints);
            this.upperBound = Array.maxOf(experimentalXPoints);


            cubicSplinesInterpolation = new CubicSplinesInterpolation(experimentalXPoints, yPoints);
            cubicSplinesInterpolationFunctions = cubicSplinesInterpolation.solution();


        } catch (Exception exception) {
            this.exit(exception.getMessage(), 0);
        }
    }

    public void exit(String message, int status) {
        System.err.printf("%s\n", message);
        /* Terminate the program with status */
        System.exit(status);
    }

    private Double[] getValuesYFromX (Double[] valuesX) {
        Double[] valuesY = new Double[valuesX.length];
        Double maxNoise;
        Double noise;
        for (int i = 0; i < valuesX.length; ++i) {
            /*if (i%2==0) {
                valuesY[i] = experimentalFunction.apply(valuesX[i]) + experimentalFunction.apply(valuesX[i])/100;
            } else valuesY[i] = experimentalFunction.apply(valuesX[i]) - experimentalFunction.apply(valuesX[i])/100;*/
            valuesY[i] = experimentalFunction.apply(valuesX[i]);
            maxNoise = 1.0;
            noise = Math.random()*maxNoise*2 - maxNoise;
            valuesY[i] += noise;
        }
        return valuesY;
    }

    @Override
    public void start(Stage stage) throws IOException {
        NumberAxis xAxis = createNumberAxis("bounds.axis.x");
        NumberAxis yAxis = createNumberAxis("bounds.axis.y");
        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setCursor(Cursor.HAND);
        Scene scene = new Scene(chart, 900, 600);
        XYChart.Series series1 = createChart(experimentalFunction);
        series1.setName("Experimental function f(x)");
        XYChart.Series series2 = createChart(cubicSplinesInterpolationFunctions, experimentalXPoints);
        //series2.setName("Cubic Splines Interpolation Functions");
        XYChart.Series series3 = createCubicChart(cubicSplinesInterpolationFunctions, experimentalXPoints);
        series3.setName("Cubic Splines Interpolation Functions");
        chart.getData().addAll(series1, series3, series2);
        stage.setTitle("Interpolation with Cubic Splines");
        stage.setScene(scene);
        stage.show();

        Group group = new Group();
        VBox vBox = new VBox();
        TextField textFieldForX = new TextField();
        Text textY = new Text();
        Button button = new Button();
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String text;
                String input = textFieldForX.getText();
                input.replaceAll(",", ".");
                try {
                    Double x = Double.parseDouble(input);
                    if ((experimentalXPoints[0] > x) || (experimentalXPoints[experimentalXPoints.length-1]) < x)
                        throw new RuntimeException();
                    text = "\nОригинальная функция:\n   y = " + experimentalFunction.apply(x) + "\nСплайны:\n   y = ";
                    for (int i = 0; i < experimentalXPoints.length-1; ++i) {
                        if ((experimentalXPoints[i] <= x) && (experimentalXPoints[i+1] >= x)) {
                            text += cubicSplinesInterpolationFunctions[i].apply(x);
                            break;
                        }
                    }

                } catch (NumberFormatException e) {
                    text = "\nНеверный формат ввода";
                } catch (RuntimeException e) {
                    text = "\nТакой x не принадлежит рассматриваемой области";
                }
                textY.setText(text);


            }
        });
        button.setText("Ок");
        vBox.getChildren().add(new Text("\nВведите координату X\n"));
        vBox.getChildren().add(textFieldForX);
        vBox.getChildren().add(button);
        vBox.getChildren().add(textY);


        Scene scene1 = new Scene(vBox, 250, 200);
        Stage anotherStage = new Stage();
        anotherStage.setScene(scene1);
        anotherStage.show();
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Если хотите выйти напишите \"exit\"");
        System.out.println("Жду координату");
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            Double valueX;
            //input.replaceAll(".", ",");
            try{
                valueX = Double.parseDouble(input);
                for (int i = 0; i < experimentalXPoints.length-1; i++) {
                    if ((valueX>experimentalXPoints[i]) && (valueX<experimentalXPoints[i+1])) {
                        System.out.println("y = " + cubicSplinesInterpolationFunctions[i].apply(valueX));
                        System.out.println("Жду координату");
                        input = scanner.nextLine();
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода");
                System.out.println("Жду координату");
                input = scanner.nextLine();
                continue;
            }

        }
        System.out.println("Вы вышли из консоли");*/
    }

    public static void main(String[] args) {
        launch();


    }

    private NumberAxis createNumberAxis(String property) {
        String pattern = props.getProperty(property);
        try {
            /* Parse a property value to Double array */
            Double[] bounds = Array.parseDoubleArray(pattern);
            if (bounds.length == 2) {
                return new NumberAxis(bounds[0], bounds[1], (bounds[1] - bounds[0]) / 16);
            }
        } catch (RuntimeException ignored) { }
        /* If impossible parse bounds array */
        System.err.printf("Warning: property \"%s\" not setted or with invalid format!\n", property);
        return new NumberAxis();
    }

    private XYChart.Series createChart(Function function) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        /* Graph step value */
        Double step = (upperBound - lowerBound) / 4096;
        /* Modify lower and upper bound */
       // lowerBound -= 128 * step;
        //upperBound += 128 * step;
        /* Add data to series */
        for (Double xPoint = lowerBound; xPoint <= upperBound; xPoint += step) {
            Double yPoint = function.apply(xPoint);
            /* If function is defined */
            if (!yPoint.isNaN() && !yPoint.isInfinite()) {
                series.getData().add(new XYChart.Data<>(xPoint, yPoint));
            }
        }
        return series;
    }

    private XYChart.Series createChart(Function[] functions, Double[] intervals) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        /* Graph step value */
        Double step = (intervals[intervals.length-1] - intervals[0]) / 4096;
        /* Modify lower and upper bound */
        //intervals[0] += 128 * step;
        //intervals[intervals.length-1] += 128 * step;
        /* Add data to series */
        for (int i = 0; i < intervals.length-1; ++i) {
            Double xPoint;
            Double yPoint;
            if (i == 0) {
                xPoint = intervals[i];
                yPoint = functions[i].apply(xPoint);
                /* If function is defined */
                if (!yPoint.isNaN() && !yPoint.isInfinite())
                    series.getData().add(new XYChart.Data<>(xPoint, yPoint));
            }
            xPoint = intervals[i+1];
            yPoint = functions[i].apply(xPoint);
            /* If function is defined */
            if (!yPoint.isNaN() && !yPoint.isInfinite())
                series.getData().add(new XYChart.Data<>(xPoint, yPoint));
        }

        return series;
    }



    private XYChart.Series createCubicChart(Function[] functions, Double[] intervals) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        /* Graph step value */
        Double step = (intervals[intervals.length-1] - intervals[0]) / 4096;
        /* Modify lower and upper bound */
        //intervals[0] += 128 * step;
        //intervals[intervals.length-1] += 128 * step;
        /* Add data to series */
        for (int i = 0; i < intervals.length-1; ++i) {
            for (Double xPoint = intervals[i]; xPoint <= intervals[i+1]; xPoint += step) {
                Double yPoint = functions[i].apply(xPoint);
                /* If function is defined */
                if (!yPoint.isNaN() && !yPoint.isInfinite()) {
                    series.getData().add(new XYChart.Data<>(xPoint, yPoint));
                }
            }
            /*Double xPoint;
            Double yPoint;
            if (i == 0) {
                xPoint = intervals[i];
                yPoint = functions[i].apply(xPoint);
                /* If function is defined
                if (!yPoint.isNaN() && !yPoint.isInfinite())
                    series.getData().add(new XYChart.Data<>(xPoint, yPoint));
            }
            xPoint = intervals[i+1];
            yPoint = functions[i].apply(xPoint);
            /* If function is defined
            if (!yPoint.isNaN() && !yPoint.isInfinite())
                series.getData().add(new XYChart.Data<>(xPoint, yPoint));*/
        }

        return series;
    }

    private XYChart.Series createChart(Function function, Double... data) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        /* Add data to series */
        for (Double xPoint : data) {
            series.getData().add(new XYChart.Data<>(xPoint, function.apply(xPoint)));
        }
        return series;
    }

    @SafeVarargs
    private final void removeChartLineSymbol(XYChart.Series<Double, Double>... series) {
        for (XYChart.Series<Double, Double> s : series) {
            removeChartLineSymbol(s);
        }
    }

    private void removeChartLineSymbol(XYChart.Series<Double, Double> series) {
        for (XYChart.Data<Double, Double> data : series.getData()) {
            /* This node is StackPane */
            data.getNode().setVisible(false);
        }
    }
}
