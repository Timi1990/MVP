package notifications;

import algorithms.search.Solution;

public class DisplaySolutionNotification implements ObservableNotification
{
    private final Solution solution;

    public DisplaySolutionNotification(Solution solution)
    {
        this.solution = solution;
    }

    @Override
    public void print()
    {
        System.out.print("Displaying solution: ");

        solution.printSolution();
    }
}
