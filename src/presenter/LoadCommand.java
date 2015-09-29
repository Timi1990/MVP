package presenter;

import model.IModel;

import java.util.List;

/**
 * Created by Timi on 9/28/2015.
 */
public class LoadCommand implements Command{

    private IModel model;

    public LoadCommand(IModel model) {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception {

        String fileName = args.get(0);
        String mazeName = args.get(1);

        model.load(fileName,mazeName);
    }
}
