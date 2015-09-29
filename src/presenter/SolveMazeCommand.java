package presenter;

import model.IModel;

import java.util.List;

/**
 * Created by Timi on 9/28/2015.
 */
public class SolveMazeCommand implements Command {

    private IModel model;

    public SolveMazeCommand(IModel model) {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception {

        String name = args.get(0);
        String algorithm = args.get(1);

        model.solve(name,algorithm);

    }
}
