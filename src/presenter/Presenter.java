package presenter;

import model.IModel;
import notifications.ObservableNotification;
import view.IView;

import java.util.*;

public class Presenter implements Observer {
	
	private IModel model;
	private IView view;
	private HashMap<String,Command> commandNameToCommand = new HashMap<String,Command>();
	
	public Presenter(IModel model,IView view) {
		this.model=model;
		this.view=view;
	}
	@Override
	public void update(Observable observable, Object obj) {

		if (observable == model) {
			ObservableNotification notification = (ObservableNotification) obj;

			view.displayData(notification);

		} else if (observable == view) {
			String currentLine = (String) obj;
			try {
				if (currentLine.equals("Exit"))

					model.exit();

				else
					doCommandIfContains(currentLine);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	private void doCommandIfContains(final String command) throws Exception
	{
		Boolean isContains = false;

		for (String commandsName : commandNameToCommand.keySet())
		{
			if(command.startsWith(commandsName))
			{
				List<String> args = convertToArrayBy(command);

				commandNameToCommand.get(commandsName).doCommand(args);

				isContains = true;
			}
		}

		if(isContains == false)
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

	public HashMap<String,Command> createAndGetCommandHashMap()
	{
		commandNameToCommand.put("Generate", new Generate3dMazeCommand(model));
		commandNameToCommand.put("Display Maze", new DisplayMazeCommand(model));
		commandNameToCommand.put("Display cross selection by",new DisplayCrossSelectionCommand(model));
		commandNameToCommand.put("Generate", new Generate3dMazeCommand(model));
		commandNameToCommand.put("Save", new SaveCommand(model));
		commandNameToCommand.put("Load", new LoadCommand(model));
		commandNameToCommand.put("File Size", new FileSizeCommand(model));
		commandNameToCommand.put("Solve", new SolveMazeCommand(model));
		commandNameToCommand.put("Display Solution", new DisplaySolutionCommand(model));
		commandNameToCommand.put("Exit", new ExitCommand(model));
		return commandNameToCommand;
	}


}
