import javax.swing.*;

// IN FUTURE THIS CLASS WILL BE USED TO CLEANUP ButtonGrid.java
public class Stepper {
    JTable table;
    String[][] data;
    int length, width;

    public Stepper(JTable table, String[][] data, int length, int width) {
        this.table = table;
        this.data = data;
        this.length = length;
        this.width = width;
    }

    public Runnable step() {
        for (int i = 0; i < table.getRowCount(); ++i) {
            for (int j = 0; j < table.getColumnCount(); ++j) {
                if (table.getValueAt(i, j) == null) {
                    data[i][j] = "";
                } else {
                    data[i][j] = table.getValueAt(i, j).toString();
                }
            }
        }
        Object[][] futureData = stepOver();

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < width; ++j) {
                if (futureData[i][j].toString().isEmpty() || futureData[i][j].equals(0))
                    table.setValueAt("", i, j);
                else
                    table.setValueAt("x", i, j);
            }
        }
        return null;
    }

    public Object [][] stepOver()
    {
        Object[][] nextGen = new Object[length][width];
        for (int i = 0; i < length; ++i)
        {
            for (int j = 0; j < width; ++j)
            {
                nextGen[i][j] = "";
            }
        }
        for (int l = 1; l < length - 1; ++l)
        {
            for (int m = 1; m < width - 1; ++m)
            {
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; ++i)
                    for (int j = -1; j <= 1; ++j){
                        if(!(data[l + i][m + j].equals("")))
                            aliveNeighbours += 1;
                    }

                if(!(data[l][m].equals("")))
                    aliveNeighbours -= 1;
                else
                    aliveNeighbours -= 0;

                if ((!data[l][m].isEmpty()) && (aliveNeighbours < 2))
                    nextGen[l][m] = "";
                else if ((!data[l][m].isEmpty()) && (aliveNeighbours > 3))
                    nextGen[l][m] = "";
                else if ((data[l][m].isEmpty()) && (aliveNeighbours == 3))
                    nextGen[l][m] = "x";
                else
                    nextGen[l][m] = data[l][m];
            }
        }
        return nextGen;
    }
}
