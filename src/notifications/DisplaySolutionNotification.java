package notifications;

import algorithms.search.Solution;

/**
 * Created by Timi on 9/28/2015.
 */
public class DisplaySolutionNotification extends ObservableNotification {

    private Solution solution;

    public DisplaySolutionNotification(Solution solution) {

        super(ObservableNotificationNames.DisplaySolutionName);
        this.solution = solution;
    }

    @Override
    public void print() {

        System.out.print("Displaying solution: ");

        solution.printSolution();
    }
}
