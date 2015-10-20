package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.MessageBox;

public class GameHandler
{
    private final Maze3d maze3d;
    private final MazeMenu mazeMenu;
    private final Canvas canvas;

    public GameHandler(Maze3d maze3d, MazeMenu mazeMenu, Canvas canvas)
    {
        this.maze3d = maze3d;
        this.mazeMenu = mazeMenu;
        this.canvas = canvas;
    }

    public void checkForWin(Position position)
    {
        Position exitPosition = maze3d.getExitPosition();

        if(exitPosition.equals(position))
        {
            MessageBox messageBox = new MessageBox(mazeMenu.shell);
            messageBox.setMessage("you win");

            messageBox.open();

            //todo need clear canvas
        }
    }
}
