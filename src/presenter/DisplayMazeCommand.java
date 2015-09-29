package presenter;

import model.IModel;

import java.util.List;

/**
 * Created by Timi on 9/28/2015.
 */
public class DisplayMazeCommand implements Command {

    private IModel model;

    public DisplayMazeCommand(IModel model) {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception {
        String name = args.get(0);

        model.displayMaze(name);
    }
}
