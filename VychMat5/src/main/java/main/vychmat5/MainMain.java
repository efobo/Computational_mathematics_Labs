package main.vychmat5;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.vychmat5.math.CubicSplinesInterpolation;
import main.vychmat5.math.MilneMethod;
import main.vychmat5.util.Array;
import main.vychmat5.util.DiffFunction;
import main.vychmat5.util.EquationsLibrary;
import main.vychmat5.util.Function;

import java.io.IOException;
import java.util.DoubleSummaryStatistics;

public class MainMain extends javafx.application.Application {
    Double[] xValues = null;
    Double[] yValues = null;
    Double[][] coordinates;
    DiffFunction diffFunction;
    MilneMethod milneMethod;
    CubicSplinesInterpolation cubicSplinesInterpolation;
    Function[] cubicSplinesInterpolationFunctions;


    public void exit(String message, int status) {
        System.err.printf("%s\n", message);
        /* Terminate the program with status */
        System.exit(status);
    }

    @Override
    public void start(Stage stage) throws IOException {
        NumberAxis xAxis = createNumberAxis(0, 1);
        NumberAxis yAxis = createNumberAxis(0, 1);
        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setCursor(Cursor.HAND);
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        // Выбор дифференциального уравнения
        Text diffFunctionsText = new Text();
        diffFunctionsText.setText("1. " + EquationsLibrary.STRING_EQUATIONS[0] +
                "\n\n2. " + EquationsLibrary.STRING_EQUATIONS[1] + "\n\n");
        ComboBox<Integer> diffFunctionsBox = new ComboBox<>();
        //y' = x - y^2
        diffFunctionsBox.getItems().add(1);
        //y' = x^2 + y/2
        diffFunctionsBox.getItems().add(2);
        diffFunctionsBox.getSelectionModel().select(0);
        TextField x0Field = new TextField();
        x0Field.setText("0");

        TextField y0Field = new TextField();
        y0Field.setText("0.3");

        TextField borderField = new TextField();
        borderField.setText("1");
        Text answerText = new Text();
        Button button = new Button();
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String answerString = "\n";
                try{
                    double x0 = Double.parseDouble(x0Field.getText());

                    double y0 = Double.parseDouble(y0Field.getText());

                    System.out.println(EquationsLibrary.EQUATIONS[diffFunctionsBox.getValue()-1]);
                    diffFunction = EquationsLibrary.EQUATIONS[diffFunctionsBox.getValue()-1];
                    double last_x = Double.parseDouble(borderField.getText());
                    milneMethod  = new MilneMethod(x0, y0, diffFunction, last_x);
                    coordinates = milneMethod.solution();
                    answerString += "Значения в некоторых точках: \n";
                    for (int i = 0; i < coordinates.length; i+=20) {
                        answerString += "x" + i + " = " + coordinates[1][0] + "    y" + i + " = " + coordinates[i][1] + "\n";
                    }
                    xValues = Array.getXfromFullArray(coordinates);
                    yValues = Array.getYfromFullArray(coordinates);
                    cubicSplinesInterpolation = new CubicSplinesInterpolation(xValues, yValues);
                    cubicSplinesInterpolationFunctions = cubicSplinesInterpolation.solution();
                    XYChart.Series series1 = createCubicChart(cubicSplinesInterpolationFunctions, xValues);

                    series1.setName("Milen's method");

                    chart.getData().clear();
                    chart.getData().addAll(series1);
                } catch (NumberFormatException e) {
                    answerString = "\n\nНеверный формат ввода";
                } catch(RuntimeException e) {
                    answerString = "\n\n" + e.getMessage();
                }
                answerText.setText(answerString);

            }
        });
        button.setText("Построить график");


        vBox.getChildren().add(new Text("\nВыберите дифференциальное уравнение\n"));
        vBox.getChildren().add(diffFunctionsText);
        vBox.getChildren().add(diffFunctionsBox);
        vBox.getChildren().add(new Text("\n\n"));
        vBox.getChildren().add(new HBox(new Text("   x0 = "), x0Field, new Text("   y0 = "), y0Field));
        vBox.getChildren().add(new Text("\n\nДо какого значения x считать?\n"));
        vBox.getChildren().add(borderField);
        vBox.getChildren().add(new Text("\n"));
        vBox.getChildren().add(button);
        vBox.getChildren().add(answerText);
        hBox.getChildren().add(vBox);
        hBox.getChildren().add(chart);

        Scene scene = new Scene(hBox, 900, 600);
        stage.setTitle("Численное дифференцирование и задача Коши");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    private NumberAxis createNumberAxis(double firstCoordinate, double secondCoordinate) {
        if (firstCoordinate < secondCoordinate) {
            return new NumberAxis(firstCoordinate, secondCoordinate, (secondCoordinate - firstCoordinate) / 16);
        }
        else if (firstCoordinate > secondCoordinate) {
            return new NumberAxis(secondCoordinate, firstCoordinate, (firstCoordinate - secondCoordinate) / 16);
        }
        System.err.printf("The same coordinates are submitted to the method \"createNumberAxis\"\n");
        return new NumberAxis();
    }

    private XYChart.Series createCubicChart(Function[] functions, Double[] intervals) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        /* Graph step value */
        Double step = (intervals[intervals.length-1] - intervals[0]) / 4096;
        /* Modify lower and upper bound */

        /* Add data to series */
        for (int i = 0; i < intervals.length-1; ++i) {
            for (Double xPoint = intervals[i]; xPoint <= intervals[i+1]; xPoint += step) {
                Double yPoint = functions[i].apply(xPoint);
                /* If function is defined */
                if (!yPoint.isNaN() && !yPoint.isInfinite()) {
                    series.getData().add(new XYChart.Data<>(xPoint, yPoint));
                }
            }

        }

        return series;
    }

    private XYChart.Series createChartByPoints (Double[] xPoints, Double[] yPoints) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        for (int i = 0; i < xPoints.length; ++i) {
            series.getData().add(new XYChart.Data<>(xPoints[i], yPoints[i]));
        }
        return series;
    }

}
