import java.io.*;


public class FileParser {
    File file;
    public FileParser(File file){
        this.file = file;
    }

    public int[][] takeMatrixFromFile() throws IOException {
        int[][] fullMatrix = {};

        BufferedReader reader = new BufferedReader(new FileReader(file));


        String line = reader.readLine();

        if (line == null) throw new NullPointerException();
        int length = Integer.valueOf(line);
        fullMatrix = new int [length][length+1];
        line = reader.readLine();

        int raw = 0;
        int column = 0;
        while(line != null){
            char prevChar = ' ';
            column = 0;
            for (int i = 0; i < line.length(); ++i) {
                if (Character.isDigit(line.charAt(i))){
                    if (prevChar == ' ') {
                        if (i != 0) column++;
                        fullMatrix[raw][column] = Character.getNumericValue(line.charAt(i));
                    } else if (prevChar == '-') {
                        if (i != 1) column++;
                        fullMatrix[raw][column] = -Character.getNumericValue(line.charAt(i));
                    } else
                        fullMatrix[raw][column] = fullMatrix[raw][column]*10 + Character.getNumericValue(line.charAt(i));
                }

                prevChar = line.charAt(i);

            }
            if (column != length)
                System.out.println("В строке " + raw + " меньше столбцов, чем предполагалось(");
            raw++;
            line = reader.readLine();

        }
        if (raw != length)
        {
            System.out.println("В файле написано меньше строк матрицы, чем предполагалось(");
        } else System.out.println("Файл успешно прочитан!");

        return fullMatrix;
    }


}
