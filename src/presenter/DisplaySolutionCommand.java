package presenter;

import model.IModel;

import java.util.List;

/**
 * Created by Timi on 9/28/2015.
 */
public class DisplaySolutionCommand implements Command {

    private IModel model;

    public DisplaySolutionCommand(IModel model) {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception {

        String name=args.get(0);

        model.displaySolution(name);

    }
}
