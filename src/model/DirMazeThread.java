package model;

import notifications.DirNotification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timi on 9/27/2015.
 */
public class DirMazeThread extends Thread{

    MazeModel mazeModel;
    DirNotification dirNotification;

    public DirMazeThread(DirNotification dirNotification,MazeModel mazeModel) {
        this.mazeModel = mazeModel;
        this.dirNotification = dirNotification;
    }

    @Override
    public void run()
    {
        String path = dirNotification.getFilePath();

        File file = new File(path);

        List<String> filesName = getChildBy(file);

        dirNotification.setFileNames(filesName);

        mazeModel.notifyObservers(dirNotification);
    }

    private List<String> getChildBy(File file)
    {
        List<String> filesName = new ArrayList<>();

        for (File child : file.listFiles())
        {
            filesName.add(child.getName());
        }
        return filesName;
    }
}
