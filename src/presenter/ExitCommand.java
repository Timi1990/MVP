package presenter;

import model.IModel;

import java.util.List;

public class ExitCommand implements Command
{
    private final IModel model;

    public ExitCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {
        model.exit();
    }
}
