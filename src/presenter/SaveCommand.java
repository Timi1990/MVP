package presenter;

import model.IModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by Timi on 9/28/2015.
 */
public class SaveCommand implements Command {

    private IModel model;

    public SaveCommand(IModel model) {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args) throws Exception {
        String mazeName = args.get(0);
        String filePath = args.get(1);

        try
        {
            model.save(filePath, mazeName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            //TO DO-Exception
        }
    }
}
