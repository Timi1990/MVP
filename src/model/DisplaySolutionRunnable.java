package model;

import algorithms.mazeGenerators.Maze3d;
import notifications.DisplaySolutionNotification;

public class DisplaySolutionRunnable implements Runnable
{
    private final MazeModel model;
    private final String mazeName;

    public DisplaySolutionRunnable(MazeModel model, String mazeName)
    {
        this.model = model;
        this.mazeName = mazeName;
    }

    @Override
    public void run()
    {
        Maze3d maze = model.getMazeByName(mazeName);

        DisplaySolutionNotification displaySolutionNotification = new DisplaySolutionNotification(model.getSolutionByMaze(maze));

        model.notifyObservers(displaySolutionNotification);
    }
}
