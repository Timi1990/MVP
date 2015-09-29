package model;

import algorithms.mazeGenerators.Maze3d;
import io.MyDecompressorInputStream;
import notifications.LoadMazeNotification;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.Callable;

/**
 * Created by Timi on 9/28/2015.
 */
public class LoadMazeCall implements Callable {

    private MazeModel model;
    private FileInputStream fileInputStream;
    private String filePath;
    private String mazeName;

    public LoadMazeCall(MazeModel model,String filePath, String mazeName) {
        this.model = model;
        this.mazeName = mazeName;
        this.filePath = filePath;
    }

    @Override
    public Object call() throws Exception {

        fileInputStream = new FileInputStream(filePath);
        InputStream in = new MyDecompressorInputStream(fileInputStream);

        byte b[] = new byte[5000];
        in.read(b);
        in.close();

        Maze3d maze3d = new Maze3d(b);
        model.putMazeAndName(mazeName, maze3d);

        LoadMazeNotification loadMazeNotification = new LoadMazeNotification(mazeName);
        model.notifyObservers(loadMazeNotification);

        return null;
    }
}
