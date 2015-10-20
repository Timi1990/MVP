package notifications;

import algorithms.mazeGenerators.Maze3d;
import model.IModel;

public class DisplayCrossSelectionNotification implements ObservableNotification<int[][]>
{
    private int[][] crossSelection;
    private IModel model;
    private String axis;
    private Integer index;
    private Maze3d maze;

    public DisplayCrossSelectionNotification(int[][] crossSelection)
    {
        this.crossSelection = crossSelection;
    }


    public DisplayCrossSelectionNotification(String axis,Integer index,Maze3d maze) {
        this.axis = axis;
        this.index = index;
        this.maze = maze;

    }
    @Override
    public void apply()
    {
        try {
            model.getCrossSelectionBy(maze,index);
        } catch (Exception e) {
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

}
