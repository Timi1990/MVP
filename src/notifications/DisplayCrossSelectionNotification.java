package notifications;

import algorithms.mazeGenerators.Maze3d;
import model.IModel;

public class DisplayCrossSelectionNotification implements ObservableNotification<int[][]>
{
    private int[][] crossSelection;
    private final String axis;
    private final Integer index;
    private final Maze3d maze;
    private IModel model;

    public DisplayCrossSelectionNotification(Maze3d maze, Integer index, String axis)
    {
        this.axis = axis;
        this.index = index;
        this.maze = maze;
    }

    @Override
    public void apply()
    {
        try
        {
            model.setNotification(this);
            model.getCrossSelectionBy(maze, index, axis);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void print()
    {
        for (int[] aCrossSelection : crossSelection)
        {
            System.out.printf("{");
            for (int anACrossSelection : aCrossSelection)
            {
                System.out.printf(anACrossSelection + ",");
            }
            System.out.printf("}");
            System.out.printf("\n");
        }
    }

    @Override
    public void init(IModel model)
    {
        this.model = model;
    }

    @Override
    public int[][] getData()
    {
        return crossSelection;
    }

    @Override
    public void setData(int[][] data)
    {
        this.crossSelection = data;
    }

}
