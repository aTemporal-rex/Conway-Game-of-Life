import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonGrid extends JPanel {

    Color selectedColor = Color.BLACK;
    String gridSize = "Small"; 
    JTable table;
    int length = 10;
    int width = 10;
    final int rulesX = 1200; // width of rules window
    final int rulesY = 500;  // height of rules window
    final int ROW_SIZE = 30, COL_SIZE = 30;
    final String [][] data = new String[ROW_SIZE][COL_SIZE];
    final JButton next;
    final JButton settings;
    final JButton randomize;
    final JButton rules;
    final JButton start;
    final JButton stop;
    final JPanel buttonPanel;
    final JScrollPane sp;
    boolean isRunning = false;
    
    public JTable createSmallTable() {
        Object[] cols = {"", "", "", "", "", "", "", "", "", "",};
        String[][] rows = { 
            { "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "",}
        };
        JTable t = new JTable(rows, cols) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                String value = (getModel().getValueAt(row, col)).toString();
                if (!value.isEmpty()) {
                    comp.setBackground(selectedColor);
                }
                else {
                    comp.setBackground(Color.white);
                }
                return comp;
            }
        };
        t.setRowHeight(50);
        return t;
    }

    public JTable createMediumTable() {
        Object[] cols = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",};
        String[][] rows = { 
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",}
        };
        JTable t = new JTable(rows, cols) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                String value = (getModel().getValueAt(row, col)).toString();
                if (!value.isEmpty()) {
                    comp.setBackground(selectedColor);
                }
                else {
                    comp.setBackground(Color.white);
                }
                return comp;
            }
        };
        t.setRowHeight(25);
        return t;
    }

    public JTable createLargeTable() {
        Object[] cols = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",};
        String[][] rows = { 
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",}
        };
        JTable t = new JTable(rows, cols) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                String value = (getModel().getValueAt(row, col)).toString();
                if (!value.isEmpty()) {
                    comp.setBackground(selectedColor);
                }
                else {
                    comp.setBackground(Color.white);
                }
                return comp;
            }
        };
        t.setRowHeight(15);
        return t;
    }

    public ButtonGrid() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        randomize = new JButton("Randomize");
        randomize.setBackground(Color.lightGray);
        next = new JButton("Next");
        next.setBackground(Color.lightGray);
        start = new JButton("Start");
        start.setBackground(Color.lightGray);
        stop = new JButton("Stop");
        stop.setBackground(Color.lightGray);
        settings = new JButton("Settings");
        settings.setBackground(Color.lightGray);
        rules = new JButton("Rules");
        rules.setBackground(Color.lightGray);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.add(randomize);
        buttonPanel.add(next);
        buttonPanel.add(start);
        buttonPanel.add(stop);
        buttonPanel.add(settings);
        buttonPanel.add(rules);

        JTextArea text = new JTextArea();
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        text.setFont(font);
        text.append("Welcome to Conway's Game of Life \n");
        text.append("To begin, fill the board with any key / text. \n" +
                        "The Randomize button allows you to randomize the board. \n" +
                        "Click Next to show the next iteration using the game's algorithm. \n" +
                        "Click Start to iterate automatically, and Stop to stop. \n" +
                        "Settings allows you to change the grid size, cell color, and listen to music!");
        text.setEditable(false);

        table = createSmallTable();
        sp = new JScrollPane(table);
        JPanel temp = new JPanel();
        temp.setLayout(new GridLayout(2,1));
        temp.add(text);
        temp.add(buttonPanel);
        frame.add(sp, BorderLayout.CENTER);
        frame.add(temp, BorderLayout.SOUTH);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setResizable(false);

        for (int i = 0; i < ROW_SIZE; ++i) {
            for (int j = 0; j < COL_SIZE; ++j) {
                data[i][j] = "";
            }
        }

        
        randomize.addActionListener(e -> {
            for (int i = 0; i < table.getRowCount(); ++i) {
                for (int j = 0; j < table.getColumnCount(); ++j) {
                    if (new Random().nextBoolean()) {
                        data[i][j] = "x";
                        table.setValueAt("x", i, j);
                    }
                    else {
                        data[i][j] = "0";
                        table.setValueAt("", i, j);
                    }
                }
            }
        });
        
        next.addActionListener(e -> step(table, data, length, width));

        settings.addActionListener(e -> {
            Settings f = new Settings(selectedColor,gridSize);
            f.setBounds(100, 100, 800, 800);
            f.setTitle("Settings");
            f.setVisible(true);
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    gridSize = f.tempSize;
                    selectedColor = f.temp;
                    if (gridSize.equals("Small")) {
                        table = createSmallTable();
                        sp.setViewportView(table);
                        length = 10;
                        width = 10;
                    }
                    if (gridSize.equals("Medium")) {
                        table = createMediumTable();
                        sp.setViewportView(table);
                        length = 20;
                        width = 20;
                    }
                    if (gridSize.equals("Large")) {
                        table = createLargeTable();
                        sp.setViewportView(table);
                        length = 30;
                        width = 30;
                    }
                }
            });
        });

        rules.addActionListener(e -> {
            Rules rulesWindow = new Rules(rulesX, rulesY);
            rulesWindow.setBounds(100, 100, rulesX, rulesY);
            rulesWindow.setTitle("Rules");
            rulesWindow.setVisible(true);
        });

        start.addActionListener(e -> {
            isRunning = true;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                if (isRunning) {
                    step(table, data, length, width);
                }
                else {
                    timer.cancel();
                    timer.purge();
                }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 770);
        });

        stop.addActionListener(e -> isRunning = false);
    }

    public void step(JTable table, String[][] data, int length, int width) {
        for (int i = 0; i < table.getRowCount(); ++i) {
            for (int j = 0; j < table.getColumnCount(); ++j) {
                if (table.getValueAt(i, j) == null) {
                    data[i][j] = "";
                } else {
                    data[i][j] = table.getValueAt(i, j).toString();
                }
            }
        }
        Object[][] futureData = stepOver(data, length, width);

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < width; ++j) {
                if (futureData[i][j].toString().isEmpty() || futureData[i][j].equals(0))
                    table.setValueAt("", i, j);
                else
                    table.setValueAt("x", i, j);
            }
        }
    }
    
    public Object [][] stepOver(Object[][] grid, int x, int y)
    { 
        Object[][] nextGen = new Object[x][y]; 
        for (int i = 0; i < x; ++i)
        {
            for (int j = 0; j < y; ++j)
            {
                nextGen[i][j] = "";
            } 
        }   
        for (int l = 1; l < x - 1; ++l)
        {
            for (int m = 1; m < y - 1; ++m)
            {
                int aliveNeighbours = 0; 
                for (int i = -1; i <= 1; ++i) 
                    for (int j = -1; j <= 1; ++j){
                        if(!(grid[l + i][m + j].equals("")))    
                            aliveNeighbours += 1; 
                    }

                if(!(grid[l][m].equals("")))
                    aliveNeighbours -= 1;
                else
                    aliveNeighbours -= 0;

                if ((!grid[l][m].toString().isEmpty()) && (aliveNeighbours < 2)) 
                    nextGen[l][m] = "";
                else if ((!grid[l][m].toString().isEmpty()) && (aliveNeighbours > 3)) 
                    nextGen[l][m] = "";
                else if ((grid[l][m].toString().isEmpty()) && (aliveNeighbours == 3)) 
                    nextGen[l][m] = "x";
                else
                    nextGen[l][m] = grid[l][m]; 
            } 
        }
        return nextGen;
    }

// --Commented out by Inspection START (4/27/2021 1:28 PM):
//    public boolean getIsRunning() {
//        return isRunning;
//    }
// --Commented out by Inspection STOP (4/27/2021 1:28 PM)


    public static void main(String[] args) {
        ButtonGrid grid = new ButtonGrid();
        Music.playMusic("Zelda & Chill - Fairy Fountain.wav");
    }
}
