import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
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
    int length = 10, width = 10;
    final int RULES_X = 1200; // Width of rules window
    final int RULES_Y = 500;  // Height of rules window
    final int SMALL_ROW_SIZE = 10, SMALL_COL_SIZE = 10, // Row and col sizes for grids; data array uses large
              MEDIUM_ROW_SIZE = 20, MEDIUM_COL_SIZE = 20,
              LARGE_ROW_SIZE = 30, LARGE_COL_SIZE = 30,
              SMALL_ROW_HEIGHT = 60, MEDIUM_ROW_HEIGHT = 30, LARGE_ROW_HEIGHT = 20;
    final String [][] data = new String[LARGE_ROW_SIZE][LARGE_COL_SIZE];
    final JButton NEXT, SETTINGS, RANDOMIZE, RULES, START, STOP;
    final JPanel BUTTON_PANEL;
    final JScrollPane SP;
    boolean isRunning = false;

    public JTable createTable(final int ROW_SIZE, final int COL_SIZE, final int ROW_HEIGHT) {
        // Assign column names as numbers 1 through COL_SIZE
        String [] colNames = new String[COL_SIZE];
        for (int i = 0; i < COL_SIZE; ++i) {
            colNames[i] = String.valueOf(i + 1);
        }

        // Fill in rowData array with empty string data
        String[][] rowData = new String[ROW_SIZE][COL_SIZE];
        for (int i = 0; i < ROW_SIZE; ++i) {
            for (int j = 0; j < COL_SIZE; ++j) {
                rowData[i][j] = "";
            }
        }

        // Creating JTable
        JTable t = new JTable(rowData, colNames) {
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

        t.setRowHeight(ROW_HEIGHT);
        return t;
    }

    public ButtonGrid() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initializing button text and colors
        RANDOMIZE = new JButton("RANDOMIZE");
        RANDOMIZE.setBackground(Color.lightGray);
        NEXT = new JButton("NEXT");
        NEXT.setBackground(Color.lightGray);
        START = new JButton("START");
        START.setBackground(Color.lightGray);
        STOP = new JButton("STOP");
        STOP.setBackground(Color.lightGray);
        SETTINGS = new JButton("SETTINGS");
        SETTINGS.setBackground(Color.lightGray);
        RULES = new JButton("RULES");
        RULES.setBackground(Color.lightGray);

        BUTTON_PANEL = new JPanel();
        BUTTON_PANEL.setLayout(new GridLayout(1,4));
        BUTTON_PANEL.add(RANDOMIZE);
        BUTTON_PANEL.add(NEXT);
        BUTTON_PANEL.add(START);
        BUTTON_PANEL.add(STOP);
        BUTTON_PANEL.add(SETTINGS);
        BUTTON_PANEL.add(RULES);

        // Text to be shown on the starting screen
        JTextArea text = new JTextArea();
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        text.setFont(font);
        text.append("Welcome to Conway's Game of Life \n");
        text.append("To begin, fill the board with any key / text. \n" +
                        "The RANDOMIZE button allows you to randomize the board. \n" +
                        "Click NEXT to show the next iteration using the game's algorithm. \n" +
                        "Click START to iterate automatically, and STOP to stop. \n" +
                        "SETTINGS allows you to change the grid size, cell color, and pause/resume the music!");
        text.setEditable(false);

        table = createTable(SMALL_ROW_SIZE, SMALL_COL_SIZE, SMALL_ROW_HEIGHT);
        SP = new JScrollPane(table);
        JPanel temp = new JPanel();
        temp.setLayout(new GridLayout(2,1));
        temp.add(text);
        temp.add(BUTTON_PANEL);
        frame.add(SP, BorderLayout.CENTER);
        frame.add(temp, BorderLayout.SOUTH);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setResizable(false);

        // Filling data array with empty string data
        for (int i = 0; i < LARGE_ROW_SIZE; ++i) {
            for (int j = 0; j < LARGE_COL_SIZE; ++j) {
                data[i][j] = "";
            }
        }
        
        RANDOMIZE.addActionListener(e -> {
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
        
        NEXT.addActionListener(e -> step(table, data, length, width));

        SETTINGS.addActionListener(e -> {
            Settings f = new Settings(selectedColor,gridSize);
            f.setBounds(100, 100, 800, 800);
            f.setTitle("Settings");
            f.setVisible(true);
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    gridSize = f.tempSize;
                    selectedColor = f.temp;
                    if (gridSize.equals("Small")) {
                        table = createTable(SMALL_ROW_SIZE, SMALL_COL_SIZE, SMALL_ROW_HEIGHT);
                        SP.setViewportView(table);
                        length = 10;
                        width = 10;
                    }
                    if (gridSize.equals("Medium")) {
                        table = createTable(MEDIUM_ROW_SIZE, MEDIUM_COL_SIZE, MEDIUM_ROW_HEIGHT);
                        SP.setViewportView(table);
                        length = 20;
                        width = 20;
                    }
                    if (gridSize.equals("Large")) {
                        table = createTable(LARGE_ROW_SIZE, LARGE_COL_SIZE, LARGE_ROW_HEIGHT);
                        SP.setViewportView(table);
                        length = 30;
                        width = 30;
                    }
                }
            });
        });

        RULES.addActionListener(e -> {
            Rules rulesWindow = new Rules(RULES_X, RULES_Y);
            rulesWindow.setBounds(100, 100, RULES_X, RULES_Y);
            rulesWindow.setTitle("Rules");
            rulesWindow.setVisible(true);
        });

        START.addActionListener(e -> {
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

        STOP.addActionListener(e -> isRunning = false);
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
