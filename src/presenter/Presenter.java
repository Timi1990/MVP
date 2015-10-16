package presenter;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.*;
import boot.GlobalThreadPool;
import model.IModel;
import notifications.ObservableNotification;
import notifications.PropertiesNotification;
import view.IView;
import view.MazeCLIView;
import view.MazeGUIView;

import java.io.IOException;
import java.util.*;

public class Presenter implements Observer
{
    private final IModel model;
    private final IView view;
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
            if (obj instanceof PropertiesNotification)
            {
                setProperties(((PropertiesNotification) obj).getProperties());
            } else
            {
                ObservableNotification notification = (ObservableNotification) obj;

                view.displayData(notification);
            }

        } else if (observable == view)
        {
            String currentLine = (String) obj;
            try
            {
                if (currentLine.equals("Exit"))
                {
                    model.exit();
                } else
                {
                    doCommandIfContains(currentLine);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
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

        if (isContains == false)
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

    public void setProperties(Properties properties)
    {
        List<String> propertiesList = properties.getPropertiesList();

        HashMap<String, Searcher> searcherFactory = new HashMap<String, Searcher>();
        HashMap<String, Maze3dGenerator> generatorsFactory = new HashMap<String, Maze3dGenerator>();

        searcherFactory.put("BFS", new BFS());
        searcherFactory.put("Astar", new Astar(new MazeManhattanDistance()));
        generatorsFactory.put("Simple maze generator", new SimpleMaze3dGenerator());
        generatorsFactory.put("My maze generator", new MyMaze3dGenerator());

        Integer numOfThreads = Integer.decode(propertiesList.get(0));
        GlobalThreadPool.getInstance().setAndCreateNumOfThreads(numOfThreads);

		String searcherName = propertiesList.get(1);
		String generator = propertiesList.get(2);
		String viewProp = propertiesList.get(3);

        if (generatorsFactory.containsKey(generator) && searcherFactory.containsKey(searcherName))
        {
            model.setMazeGenerator(generatorsFactory.get(generator));

			model.setSearcher(searcherFactory.get(searcherName));
		}
		if(viewProp.equals("CLI"))
		{
			view.exitFromGui();
			view = new MazeCLIView();
		}
//		try {
//			HashMap<Maze3d,Solution> hm = this.model.loadSolutionsForMazes();
//			this.model.setMazeAndSolutionMap(hm);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}

	}

	public void setView(MazeCLIView view)
	{
		this.view = view;
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
