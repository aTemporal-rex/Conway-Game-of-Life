import org.junit.Test;
import static org.junit.Assert.*;

public class ButtonGridTest {
    private final ButtonGrid bg = new ButtonGrid();

   @Test
    public void testFewerThanTwoLiveNeighbors() {
        //live cell with fewer than 2 live neighbors should die.
        //The examined cell is center cell: current[1][1].
        //empty is dead, x is live.

        Object [][] current = {
            { "", "", "",},
            { "", "x", "",},
            { "", "", "",},
        };

        Object [][] nextGeneration = bg.stepOver(current, 3, 3);
        assertEquals("", nextGeneration[1][1]);
    }

    @Test
    public void testTwoLiveNeighbors() {
        //live cell with 2 live neighbors should live.
        //The examined cell is center cell: current[1][1].
        //empty is dead, x is live.

        Object [][] current = {
                { "x", "x", "",},
                { "", "x", "",},
                { "", "", "",},
        };

        Object [][] nextGeneration = bg.stepOver(current, 3, 3);
        assertEquals("x", nextGeneration[1][1]);
    }

    @Test
    public void testThreeLiveNeighbors() {
        //live cell with 3 live neighbors should live.
        //The examined cell is center cell: current[1][1].
        //empty is dead, x is live.

        Object [][] current = {
                { "x", "x", "x",},
                { "", "x", "",},
                { "", "", "",},
        };

        Object [][] nextGeneration = bg.stepOver(current, 3, 3);
        assertEquals("x", nextGeneration[1][1]);
    }

    @Test
    public void testMoreThanThreeLiveNeighbors() {
        //live cell with more than 3 live neighbors should die.
        //The examined cell is center cell: current[1][1].
        //empty is dead, x is live.

        Object [][] current = {
                { "x", "x", "x",},
                { "", "x", "",},
                { "", "x", "",},
        };

        Object [][] nextGeneration = bg.stepOver(current, 3, 3);
        assertEquals("", nextGeneration[1][1]);
    }

    @Test
    public void testDeadCellThreeLiveNeighbors() {
        //dead cell with 3 live neighbors should become a live cell.
        //The examined cell is center cell: current[1][1].
        //empty is dead, x is live.

        Object [][] current = {
                { "x", "x", "x",},
                { "", "", "",},
                { "", "", "",},
        };

        Object [][] nextGeneration = bg.stepOver(current, 3, 3);
        assertEquals("x", nextGeneration[1][1]);
    }

}