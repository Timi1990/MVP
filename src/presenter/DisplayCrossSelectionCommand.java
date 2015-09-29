package presenter;

import model.IModel;

import java.util.List;

/**
 * Created by Timi on 9/28/2015.
 */
public class DisplayCrossSelectionCommand implements Command {

    private IModel model;

    public DisplayCrossSelectionCommand(IModel model) {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception {

        String axis = args.get(0);
        Integer index = Integer.decode(args.get(1));
        String name = args.get(2);

        model.getCrossSelectionBy(axis,name,index);
    }
}
