package notifications;

public class DisplayCrossSelectionNotification implements ObservableNotification
{
    private final int[][] crossSelection;

    public DisplayCrossSelectionNotification(int[][] crossSelection)
    {
        this.crossSelection = crossSelection;
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
}
