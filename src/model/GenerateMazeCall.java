package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MazeArgumentsForInit;
import notifications.GenerateMazeNotification;

import java.util.concurrent.Callable;

public class GenerateMazeCall implements Callable<Maze3d>
{
    private final String mazeName;
    private final MazeArgumentsForInit mazeArgumentsForInit;
    private final MazeModel mazeModel;

    public GenerateMazeCall(String mazeName, MazeArgumentsForInit mazeArgumentsForInit, MazeModel mazeModel)
    {
        this.mazeName = mazeName;
        this.mazeArgumentsForInit = mazeArgumentsForInit;
        this.mazeModel = mazeModel;
    }

    @Override
    public Maze3d call() throws Exception
    {
        Maze3dGenerator maze3dGenerator = mazeModel.getMazeGenerator();

        Maze3d maze = maze3dGenerator.generate(mazeArgumentsForInit);

//        GenerateMazeNotification generateMazeNotification = new GenerateMazeNotification(mazeName, model, name, dimension, rows, columns);

        //todo ask timi for twice
//        mazeModel.putMazeAndName(mazeName, maze);

//        mazeModel.notifyObservers(generateMazeNotification);

        //todo ask yoav->
        ((GenerateMazeNotification) mazeModel.getNotification()).setMaze(maze);
        mazeModel.notifyObservers(mazeModel.getNotification());
        return maze;
    }
}
