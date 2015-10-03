package model;

import algorithms.mazeGenerators.Maze3d;
import notifications.DisplayMazeNotification;

import java.util.concurrent.Callable;

/**
 * Created by Timi on 9/28/2015.
 */
public class DisplayMazeCall implements Callable<Object>{

    private String mazeName;
    private MazeModel model;

    public DisplayMazeCall(String mazeName,MazeModel model) {
        this.mazeName = mazeName;
        this.model = model;
    }

    @Override
    public Object call() throws Exception {

        Maze3d maze = model.getMazeByName(mazeName);

        DisplayMazeNotification displayMazeNotification = new DisplayMazeNotification(maze);

        model.notifyObservers(displayMazeNotification);

        return null;
    }


}
