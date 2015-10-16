package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class GameHandler
{
    private final Maze3d maze3d;

    public GameHandler(Maze3d maze3d)
    {
        this.maze3d = maze3d;
    }

    public void checkForWin(int z, int y, int x)
    {
        Position exitPosition = maze3d.getExitPosition();

        Position position = new Position(z,y,x);

        if(exitPosition.equals(position))
        {
            WinWindow winWindow = new WinWindow(500,500);
//
            winWindow.run();
        }
    }
}
