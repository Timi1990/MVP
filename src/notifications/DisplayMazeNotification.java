package notifications;

import algorithms.mazeGenerators.Maze3d;
import model.IModel;

public class DisplayMazeNotification implements ObservableNotification<Maze3d>
{
    private final Maze3d maze;
    private IModel model;

    public DisplayMazeNotification(Maze3d maze)
    {
        this.maze = maze;
    }


    @Override
    public void apply() {

    }

    @Override
    public void print()
    {
        maze.printMaze();
    }

    @Override
    public void init(IModel model) {
        this.model = model;
    }

    @Override
    public Maze3d getData()
    {
        return maze;
    }
}
