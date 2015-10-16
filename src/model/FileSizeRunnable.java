package model;

import algorithms.mazeGenerators.Maze3d;
import notifications.FileSizeNotification;

public class FileSizeRunnable implements Runnable
{
    private final MazeModel model;
    private final String mazeName;

    public FileSizeRunnable(MazeModel model, String mazeName)
    {
        this.model = model;
        this.mazeName = mazeName;
    }

    @Override
    public void run()
    {
        Maze3d maze = model.getMazeByName(mazeName);

        long length = maze.toByteArray().length;

        FileSizeNotification fileSizeNotification = new FileSizeNotification(length, mazeName);

        model.notifyObservers(fileSizeNotification);
    }
}
