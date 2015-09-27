package model;

import notifications.LoadMazeObservableNotification;

import java.io.IOException;

/**
 * Created by Timi on 9/26/2015.
 */
public class MazeLoaderThread extends Thread {

    MazeModel mazeModel;
    MyModel myModel;
    LoadMazeObservableNotification loadMazeObservableNotification;

    public MazeLoaderThread(MazeModel mazeModel, MyModel myModel,LoadMazeObservableNotification loadMazeObservableNotification) {
        this.mazeModel = mazeModel;
        this.myModel = myModel;
        this.loadMazeObservableNotification = loadMazeObservableNotification;
    }
    
    
    @Override
    public void run() {
        try {
            myModel.load(loadMazeObservableNotification.getFilePath(),loadMazeObservableNotification.getMazeName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mazeModel.notifyObservers(loadMazeObservableNotification);
    }
}
