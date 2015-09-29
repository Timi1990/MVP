package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MazeArgumentsForInit;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import notifications.GenerateMazeNotification;

import java.util.concurrent.Callable;

/**
 * Created by Timi on 9/27/2015.
 */
public class GenerateMazeCall implements Callable<Maze3d>{


    private MazeModel mazeModel;
    private GenerateMazeNotification generateMazeNotification;

    public GenerateMazeCall(String mazeName,Integer dimension,Integer rows,Integer columns, MazeModel mazeModel) {
        generateMazeNotification=new GenerateMazeNotification(mazeName, dimension, rows, columns);
        this.mazeModel = mazeModel;
    }

    @Override
    public Maze3d call() throws Exception {
        MazeArgumentsForInit mazeArgumentsForInit = new MazeArgumentsForInit(generateMazeNotification.getDimension(),generateMazeNotification.getRows(),generateMazeNotification.getColumns());

        Maze3d maze = new MyMaze3dGenerator().generate(mazeArgumentsForInit);

        mazeModel.notifyObservers(generateMazeNotification);

        return maze;
    }
}
