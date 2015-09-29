package notifications;

/**
 * Created by Timi on 9/28/2015.
 */
public class DisplayCrossSelectionNotification extends ObservableNotification {

    private int[][] crossSelection;


    public DisplayCrossSelectionNotification() {
        super(ObservableNotificationNames.DisplayCrossSelectionName);
    }

    @Override
    public void print() {
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

    public int[][] getCrossSelection() {
        return crossSelection;
    }

    public void setCrossSelection(int[][] crossSelection) {
        this.crossSelection = crossSelection;
    }
}
