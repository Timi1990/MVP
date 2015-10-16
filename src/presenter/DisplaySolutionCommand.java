package presenter;

import model.IModel;

import java.util.List;

public class DisplaySolutionCommand implements Command
{
    private final IModel model;

    public DisplaySolutionCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {
        String name = args.get(0);

        model.displaySolution(name);
    }
}
