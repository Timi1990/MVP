package presenter;

import model.IModel;

import java.util.List;

/**
 * Created by Timi on 9/28/2015.
 */
public class ExitCommand implements Command {

    private IModel model;

    public ExitCommand(IModel model) {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception {
        model.exit();
    }
}
