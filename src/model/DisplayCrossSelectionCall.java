package model;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;
import notifications.DisplayCrossSelectionNotification;

import java.util.concurrent.Callable;

/**
 * Created by Timi on 9/28/2015.
 */
public class DisplayCrossSelectionCall implements Callable {

    private MazeModel model;
    private Maze3d maze;
    private String axis;
    private Integer index;

    public DisplayCrossSelectionCall(MazeModel model,String axis, Maze3d maze, Integer index) {
        this.model = model;
        this.maze = maze;
        this.axis = axis;
        this.index = index;
    }

    @Override
    public Object call() throws Exception {

        DisplayCrossSelectionNotification displayCrossSelectionNotification = new DisplayCrossSelectionNotification();

        displayCrossSelectionNotification.setCrossSelection(getCrossSelectionBy(axis,maze,index));

        model.notifyObservers(displayCrossSelectionNotification);

        return null;
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
        return null;
    }
}
