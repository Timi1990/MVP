package model;

import notifications.GenerateMazeNotification;

/**
 * Created by Timi on 9/27/2015.
 */
public class GenerateMazeThread extends Thread{

    private MyModel myModel;
    private MazeModel mazeModel;
    private final GenerateMazeNotification generateMazeNotification;

    public GenerateMazeThread(GenerateMazeNotification generateMazeNotification, MazeModel mazeModel, MyModel myModel) {
        this.generateMazeNotification = generateMazeNotification;
        this.mazeModel = mazeModel;
        this.myModel = myModel;
    }

    @Override
    public void run()
    {
        myModel.generateMaze3dBy(generateMazeNotification.getMazeName(),generateMazeNotification.getDimension(),generateMazeNotification.getRows(),generateMazeNotification.getColumns());
        mazeModel.notifyObservers(generateMazeNotification);
    }


}
