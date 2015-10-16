package model;

import algorithms.mazeGenerators.Maze3d;
import notifications.SaveMazeNotification;

import java.io.IOException;
import java.io.OutputStream;

public class SaveMazeRunnable implements Runnable
{
    private final OutputStream out;
    private final String mazeName;
    private final MazeModel model;

    public SaveMazeRunnable(OutputStream out, String mazeName, MazeModel model)
    {
        this.out = out;
        this.mazeName = mazeName;
        this.model = model;
    }

    @Override
    public void run()
    {
        try
        {
            Maze3d maze = model.getMazeByName(mazeName);

            out.write(maze.toByteArray());

            out.close();

            model.notifyObservers(new SaveMazeNotification(mazeName));
        }
        catch (IOException e)
        {
            //todo: print message to user.
        }
    }
}
