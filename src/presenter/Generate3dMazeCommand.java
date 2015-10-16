package presenter;

import model.IModel;

import java.util.List;

public class Generate3dMazeCommand implements Command
{
    private final IModel model;

    Generate3dMazeCommand(IModel model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception
    {
        String mazeName = args.get(0);
        Integer dimension = Integer.decode(args.get(1));
        Integer rows = Integer.decode(args.get(2));
        Integer columns = Integer.decode(args.get(3));

        model.generateMaze(mazeName, dimension, rows, columns);
    }
}
