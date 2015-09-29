package model;

import algorithms.mazeGenerators.Maze3d;
import notifications.DisplaySolutionNotification;

import java.util.concurrent.Callable;

/**
 * Created by Timi on 9/28/2015.
 */
public class DisplaySolutionCall implements Callable {

    private MazeModel model;
    private String mazeName;

    public DisplaySolutionCall(MazeModel model, String mazeName) {
        this.model = model;
        this.mazeName = mazeName;
    }

    @Override
    public Object call() throws Exception {

        Maze3d maze = model.getMazeByName(mazeName);

        DisplaySolutionNotification displaySolutionNotification = new DisplaySolutionNotification(model.getSolutionByMaze(maze));

        model.notifyObservers(displaySolutionNotification);

        return null;
    }
}
