package model;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;
import notifications.ObservableNotification;

public class DisplayCrossSelectionRunnable implements Runnable
{
    private final MazeModel model;
    private final Maze3d maze;
    private final String axis;
    private final Integer index;

    public DisplayCrossSelectionRunnable(MazeModel model, String axis, Maze3d maze, Integer index)
    {
        this.model = model;
        this.maze = maze;
        this.axis = axis;
        this.index = index;
    }

    @Override
    public void run()
    {
        try
        {
            int[][] crossSelectionBy = getCrossSelectionBy(axis, maze, index);

            ObservableNotification<int[][]> notification = model.<int[][]>getNotification();
            notification.<int[][]>setData(crossSelectionBy);

            model.notifyObservers(notification);
        }
        catch (IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
    }

    private int[][] getCrossSelectionBy(String axis, Maze3d maze3d, Integer index) throws IndexOutOfBoundsException
    {
        switch (axis)
        {
            case "X":
            {
                return maze3d.getCrossSectionByX(index);
            }
            case "Y":
            {
                return maze3d.getCrossSectionByY(index);
            }
            case "Z":
            {
                return maze3d.getCrossSectionByZ(index);
            }
        }
        throw new IndexOutOfBoundsException(axis);
    }
}
