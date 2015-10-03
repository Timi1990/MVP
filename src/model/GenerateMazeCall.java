package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MazeArgumentsForInit;
import notifications.GenerateMazeNotification;

import java.util.concurrent.Callable;

/**
 * Created by Timi on 9/27/2015.
 */
public class GenerateMazeCall implements Callable<Maze3d>{


    private MazeModel mazeModel;
    private GenerateMazeNotification generateMazeNotification;
    private String mazeName;

    public GenerateMazeCall(String mazeName,Integer dimension,Integer rows,Integer columns, MazeModel mazeModel) {
        generateMazeNotification=new GenerateMazeNotification(mazeName, dimension, rows, columns);
        this.mazeModel = mazeModel;
        this.mazeName = mazeName;
    }

    @Override
    public Maze3d call() throws Exception {
        MazeArgumentsForInit mazeArgumentsForInit = new MazeArgumentsForInit(generateMazeNotification.getDimension(),generateMazeNotification.getRows(),generateMazeNotification.getColumns());

        Maze3dGenerator maze3dGenerator = mazeModel.getMazeGenerator();

        Maze3d maze = maze3dGenerator.generate(mazeArgumentsForInit);

        mazeModel.putMazeAndName(mazeName,maze);

        mazeModel.notifyObservers(generateMazeNotification);

        return maze;
    }
}
