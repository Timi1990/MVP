package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MazeArgumentsForInit;
import notifications.ObservableNotification;

import java.util.concurrent.Callable;

public class GenerateMazeCall implements Callable<Maze3d>
{
    private final MazeArgumentsForInit mazeArgumentsForInit;
    private final MazeModel mazeModel;

    public GenerateMazeCall(MazeArgumentsForInit mazeArgumentsForInit, MazeModel mazeModel)
    {
        this.mazeArgumentsForInit = mazeArgumentsForInit;
        this.mazeModel = mazeModel;
    }

    @Override
    public Maze3d call() throws Exception
    {
        Maze3dGenerator maze3dGenerator = mazeModel.getMazeGenerator();

        Maze3d maze = maze3dGenerator.generate(mazeArgumentsForInit);

        ObservableNotification<Maze3d> notification = mazeModel.<Maze3d>getNotification();
        notification.setData(maze);

        mazeModel.notifyObservers(notification);

        return maze;
    }
}
