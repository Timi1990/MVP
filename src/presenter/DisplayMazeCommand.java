package presenter;

import model.IModel;

import java.util.List;

public class DisplayMazeCommand implements Command
{
    private final IModel model;

    public DisplayMazeCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {
        String name = args.get(0);

        model.displayMaze(name);
    }
}
