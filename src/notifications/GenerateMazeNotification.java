package notifications;


import algorithms.mazeGenerators.Maze3d;
import model.IModel;

public class GenerateMazeNotification implements ObservableNotification
{
    private final String mazeName;
    private final int dimension;
    private final int rows;
    private final int columns;
    private Maze3d maze;
    private IModel model;


    public GenerateMazeNotification(String mazeName, int dimension, int rows, int columns)
    {
//        super(mazeName);
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
    public <T> T getData() {
       return (T) maze;
    }

    public String getMazeName() {
        return mazeName;
    }

    public int getDimension() {
        return dimension;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setMaze(Maze3d maze)
    {
        this.maze = maze;
    }
}
