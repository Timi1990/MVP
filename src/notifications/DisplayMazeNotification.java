package notifications;

import algorithms.mazeGenerators.Maze3d;

public class DisplayMazeNotification implements ObservableNotification
{
    private final Maze3d maze;

    public DisplayMazeNotification(Maze3d maze)
    {
        this.maze = maze;
    }

    @Override
    public void print()
    {
        maze.printMaze();
    }

}
