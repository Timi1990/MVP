package notifications;

import model.IModel;

public class DisplayCrossSelectionNotification implements ObservableNotification
{
    private final int[][] crossSelection;
    private IModel model;

    public DisplayCrossSelectionNotification(int[][] crossSelection)
    {
        this.crossSelection = crossSelection;
    }


    @Override
    public void apply() {

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
    public void init(IModel model) {
        this.model = model;
    }

    @Override
    public <T> T getData() {
        return (T)crossSelection;
    }
}
