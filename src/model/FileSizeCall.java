package model;

import algorithms.mazeGenerators.Maze3d;
import notifications.FileSizeNotification;

import java.util.concurrent.Callable;

/**
 * Created by Timi on 9/28/2015.
 */
public class FileSizeCall implements Callable {

    private MazeModel model;
    private String mazeName;

    public FileSizeCall(MazeModel model, String mazeName) {
        this.model = model;
        this.mazeName = mazeName;
    }

    @Override
    public Object call() throws Exception {
        Maze3d maze = model.getMazeByName(mazeName);

        long length = maze.toByteArray().length;

        FileSizeNotification fileSizeNotification = new FileSizeNotification(length,mazeName);

        model.notifyObservers(fileSizeNotification);
        return null;
    }
}
