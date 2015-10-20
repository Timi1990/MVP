package notifications;

import algorithms.search.Solution;
import model.IModel;

public class DisplaySolutionNotification implements ObservableNotification<Solution>
{
    private Solution solution;
    private IModel model;

    public DisplaySolutionNotification(Solution solution)
    {
        this.solution = solution;
    }


    @Override
    public void apply() {

    }

    @Override
    public void print()
    {
        System.out.print("Displaying solution: ");

        solution.printSolution();
    }

    @Override
    public void init(IModel model) {
        this.model = model;
    }

    @Override
    public Solution getData()
    {
        return solution;
    }

    @Override
    public void setData(Solution data)
    {
        this.solution = data;
    }
}
