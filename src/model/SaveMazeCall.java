package model;

import algorithms.mazeGenerators.Maze3d;
import notifications.SaveMazeNotification;

import java.io.OutputStream;
import java.util.concurrent.Callable;

/**
 * Created by Timi on 9/28/2015.
 */
public class SaveMazeCall implements Callable {

    private OutputStream out;
    private String mazeName ;
    private MazeModel model;

    public SaveMazeCall(OutputStream out, String mazeName,MazeModel model) {
        this.out = out;
        this.mazeName = mazeName;
        this.model = model;
    }

    @Override
    public Object call() throws Exception {

        Maze3d maze = model.getMazeByName(mazeName);

        out.write(maze.toByteArray());

        out.close();

        model.notifyObservers(new SaveMazeNotification(mazeName));

        return null;
    }
}
