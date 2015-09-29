package notifications;

import algorithms.mazeGenerators.Maze3d;

/**
 * Created by Timi on 9/28/2015.
 */
public class DisplayMazeNotification extends ObservableNotification {

    private Maze3d maze;

    public DisplayMazeNotification()
    {
        super(ObservableNotificationNames.DisplayMazeNotificationName);
    }

    @Override
    public void print() {
        maze.printMaze();
    }

    public Maze3d getMaze() {
        return maze;
    }

    public void setMaze(Maze3d maze) {
        this.maze = maze;
    }
}
