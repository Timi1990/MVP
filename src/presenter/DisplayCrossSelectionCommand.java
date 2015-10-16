package presenter;

import model.IModel;

import java.util.List;

public class DisplayCrossSelectionCommand implements Command
{
    private final IModel model;

    public DisplayCrossSelectionCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {

        String axis = args.get(0);
        Integer index = Integer.decode(args.get(1));
        String name = args.get(2);

        model.getCrossSelectionBy(axis, name, index);
    }
}
