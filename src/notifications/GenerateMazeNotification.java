package notifications;

import algorithms.mazeGenerators.Maze3d;
import model.IModel;

public class GenerateMazeNotification implements ObservableNotification<Maze3d>
{
    private final String mazeName;
    private final int dimension;
    private final int rows;
    private final int columns;
    private Maze3d maze;
    private IModel model;


    public GenerateMazeNotification(String mazeName, int dimension, int rows, int columns)
    {
        this.mazeName = mazeName;
        this.dimension = dimension;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void apply()
    {
        try
        {
            model.setNotification(this);
            model.generateMaze(mazeName,dimension,rows,columns);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void print() {

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

    public String getMazeName() {
        return mazeName;
    }
}
