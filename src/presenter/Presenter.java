package presenter;

import model.IModel;
import notifications.ObservableNotification;
import view.IView;

import java.util.*;

public class Presenter implements Observer
{
    private IModel model;
    private IView view;
    private final HashMap<String, Command> commandNameToCommand = new HashMap<String, Command>();

    public Presenter(IModel model, IView view)
    {
        this.model = model;
        this.view = view;
    }

    @Override
    public void update(Observable observable, Object obj)
    {
        if (observable == model)
        {
            view.handleData((ObservableNotification) obj);

        } else
        {
            if (observable instanceof IView)
            {
                ObservableNotification observableNotification = (ObservableNotification) obj;
                observableNotification.init(model);
                observableNotification.apply();
            } else
            {
                String currentLine = (String) obj;
                try
                {
                    doCommandIfContains(currentLine);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doCommandIfContains(final String command) throws Exception
    {
        Boolean isContains = false;

        for (String commandsName : commandNameToCommand.keySet())
        {
            if (command.startsWith(commandsName))
            {
                List<String> args = convertToArrayBy(command);

                commandNameToCommand.get(commandsName).doCommand(args);

                isContains = true;
            }
        }

        if (!isContains)
        {
            view.handleCommandNotFound();
        }
    }

    private List<String> convertToArrayBy(String command)
    {
        String substring = substring(command);

        String[] split = substring.split("\\s+");
        return Arrays.asList(split);
    }

    private String substring(String command)
    {
        int start = command.indexOf('<');
        int end = command.lastIndexOf('>');
        return command.substring(start + 1, end);
    }

    public void setView(IView view)
    {
        this.view = view;
        createAndGetCommandHashMap();
    }

    public void createAndGetCommandHashMap()
    {
        commandNameToCommand.put("Generate", new Generate3dMazeCommand(model));
        commandNameToCommand.put("Display Maze", new DisplayMazeCommand(model));
        commandNameToCommand.put("Display cross selection by", new DisplayCrossSelectionCommand(model));
        commandNameToCommand.put("Generate", new Generate3dMazeCommand(model));
        commandNameToCommand.put("Save", new SaveCommand(model));
        commandNameToCommand.put("Load", new LoadCommand(model));
        commandNameToCommand.put("File Size", new FileSizeCommand(model));
        commandNameToCommand.put("Solve", new SolveMazeCommand(model));
        commandNameToCommand.put("Display Solution", new DisplaySolutionCommand(model));
        commandNameToCommand.put("Exit", new ExitCommand(model));
    }

}
