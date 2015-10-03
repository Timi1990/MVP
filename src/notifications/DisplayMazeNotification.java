package notifications;

import algorithms.mazeGenerators.Maze3d;

/**
 * Created by Timi on 9/28/2015.
 */
public class DisplayMazeNotification extends ObservableNotification {

    private Maze3d maze;

    public DisplayMazeNotification(Maze3d maze)
    {
        super(ObservableNotificationNames.DisplayMazeNotificationName);
        this.maze = maze;
    }


    @Override
    public void print() {
        maze.printMaze();
    }

}
