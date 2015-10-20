package notifications;

import algorithms.mazeGenerators.Maze3d;
import model.IModel;

public class DisplayMazeNotification implements ObservableNotification<Maze3d>
{
    private IModel model;
    private Maze3d maze;

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

    @Override
    public void setData(Maze3d data)
    {
        this.maze = data;
    }
}
