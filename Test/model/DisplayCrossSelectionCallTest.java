package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MazeArgumentsForInit;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Timi on 10/1/2015.
 */
public class DisplayCrossSelectionCallTest {

    MazeArgumentsForInit mazeArgumentsForInit1;
    Maze3d maze1;
    Maze3d maze2;
    MazeArgumentsForInit mazeArgumentsForInit2;

    @Before
    public void setUp() throws Exception
    {
        mazeArgumentsForInit1 = new MazeArgumentsForInit(3,5,5);

        mazeArgumentsForInit2 = new MazeArgumentsForInit(1,1,1);

    }

    @Test
    public void bla()
    {
        maze1 = new MyMaze3dGenerator().generate(mazeArgumentsForInit1);
        maze2 = new MyMaze3dGenerator().generate(mazeArgumentsForInit2);
        assertEquals((Object)maze1,(Object)maze2);
    }
}