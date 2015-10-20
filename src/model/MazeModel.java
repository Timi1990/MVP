package model;

import algorithms.mazeGenerators.*;
import algorithms.search.*;
import boot.GlobalThreadPool;
import io.MyCompressorOutputStream;
import notifications.DisplayMazeExistsNotification;
import notifications.ObservableNotification;
import presenter.Properties;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MazeModel extends Observable implements IModel
{
    private HashMap<Maze3d, Solution> mazeAndSolution = new HashMap<Maze3d, Solution>();
    private HashMap<String, Maze3d> mazeAndName = new HashMap<String, Maze3d>();
    private Maze3dGenerator mazeGenerator;
    private Searcher searcher;
    private ObservableNotification notification;

    @Override
    public <T> ObservableNotification<T> getNotification()
    {
        return notification;
    }

    @Override
    public <T> void setNotification(ObservableNotification<T> notification)
    {
        this.notification = notification;
    }

    public Searcher getSearcher()
    {
        return searcher;
    }

    @Override
    public Maze3d getMazeByName(String mazeName)
    {
        return mazeAndName.get(mazeName);
    }

    @Override
    public Solution getSolutionByName(String mazeName)
    {

        return mazeAndSolution.get(mazeName);
    }

    public Maze3dGenerator getMazeGenerator()
    {
        return mazeGenerator;
    }

    @Override
    public void generateMaze(String mazeName, Integer dimension, Integer rows, Integer columns) throws Exception
    {
        setChanged();

        if (mazeAndName.containsKey(mazeName))
        {
            DisplayMazeExistsNotification displayMazeExistsNotification = new DisplayMazeExistsNotification();

            notifyObservers(displayMazeExistsNotification);

        } else
        {
            MazeArgumentsForInit mazeArgumentsForInit = new MazeArgumentsForInit(dimension, rows, columns);

            GenerateMazeCall generateMazeCall = new GenerateMazeCall(mazeArgumentsForInit, this);

            Future<Maze3d> future = GlobalThreadPool.getInstance().addCallableToPool(generateMazeCall);

            Maze3d maze = future.get();

            mazeAndName.put(mazeName, maze);

        }
    }

    @Override
    public void displayMaze(String mazeName) throws Exception
    {
        setChanged();

        DisplayMazeRunnable displayMazeRunnable = new DisplayMazeRunnable(mazeName, this);

        Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(displayMazeRunnable);

        future.get();
    }

    @Override
    public void getCrossSelectionBy(String axis, String mazeName, Integer index) throws Exception
    {
        setChanged();

        if (mazeAndName.containsKey(mazeName))
        {
            Maze3d maze = getMazeByName(mazeName);

            DisplayCrossSelectionRunnable displayCrossSelectionRunnable = new DisplayCrossSelectionRunnable(this, axis, maze, index);

            Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(displayCrossSelectionRunnable);

            future.get();
        }
    }

    @Override
    public void getCrossSelectionBy(Maze3d maze, Integer index, String axis) throws Exception
    {
        setChanged();

        DisplayCrossSelectionRunnable displayCrossSelectionRunnable = new DisplayCrossSelectionRunnable(this, axis, maze, index);

        Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(displayCrossSelectionRunnable);

        future.get();

    }

    @Override
    public void save(String filePath, String mazeName) throws Exception
    {
        setChanged();

        if (mazeAndName.containsKey(mazeName))
        {
            SaveMazeRunnable saveMazeRunnable = new SaveMazeRunnable(new MyCompressorOutputStream(new FileOutputStream(filePath)), mazeName, this);

            Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(saveMazeRunnable);

            future.get();
        }
    }

    @Override
    public void load(String filePath, String mazeName) throws Exception
    {
        setChanged();

        LoadMazeRunnable loadMazeRunnable = new LoadMazeRunnable(this, filePath, mazeName);

        Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(loadMazeRunnable);

        future.get();
    }

    @Override
    public void putMazeAndName(String mazeName, Maze3d maze)
    {
        mazeAndName.put(mazeName, maze);
    }

    @Override
    public void solve(String name) throws Exception
    {
        setChanged();

        if (mazeAndName.containsKey(name))
        {
            if (mazeAndSolution.containsKey(mazeAndName.get(name)))
            {
                DisplayMazeExistsNotification displayMazeExistsNotification = new DisplayMazeExistsNotification();

                notifyObservers(displayMazeExistsNotification);
            } else
            {
                SolveMazeCall solveMazeCall = new SolveMazeCall(name, this);

                Future<Solution> future = GlobalThreadPool.getInstance().addCallableToPool(solveMazeCall);

                Solution solution = future.get();

                mazeAndSolution.put(mazeAndName.get(name), solution);
            }
        }

    }

    @Override
    public Searcher getAlgorithm()
    {
        return searcher;
    }

    @Override
    public void putMazeAndSolution(Maze3d maze, Solution solution)
    {
        mazeAndSolution.put(maze, solution);
    }

    @Override
    public void displaySolution(String mazeName) throws Exception
    {
        setChanged();

        DisplaySolutionRunnable displaySolutionRunnable = new DisplaySolutionRunnable(this, mazeName);

        Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(displaySolutionRunnable);

        future.get();
    }

    @Override
    public void fileSize(String mazeName) throws Exception
    {
        setChanged();

        if (mazeAndName.containsKey(mazeName))
        {
            FileSizeRunnable fileSizeRunnable = new FileSizeRunnable(this, mazeName);

            Future<?> future = GlobalThreadPool.getInstance().addRunnableToPool(fileSizeRunnable);

            future.get();
        }
    }

    @Override
    public void saveSolutionsBeforeExit(String path) throws IOException
    {
        if (path == null)
        {
            Scanner scanner = new Scanner(System.in);
            String filePath;

            System.out.println("Enter path to save maze's solutions");
            filePath = scanner.next();

            ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));

            out.writeObject(mazeAndSolution);
            out.flush();
            out.close();
        } else
        {
            ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(path)));

            out.writeObject(mazeAndSolution);
            out.flush();
            out.close();
        }
    }

    @Override
    public HashMap<Maze3d, Solution> loadSolutionsForMazes() throws IOException, ClassNotFoundException
    {

        Scanner scanner = new Scanner(System.in);
        String filePath;

        System.out.println("Enter path(.gz) to load from solutions:");
        filePath = scanner.next();

        ObjectInputStream in = new ObjectInputStream(new GZIPInputStream((new FileInputStream(filePath))));

        HashMap<Maze3d, Solution> hm = new HashMap<Maze3d, Solution>();

        hm = (HashMap<Maze3d, Solution>) in.readObject();

        return hm;

    }

    @Override
    public void setMazeAndSolutionMap(HashMap<Maze3d, Solution> hm)
    {
        this.mazeAndSolution = hm;
    }

    @Override
    public void exit() throws IOException
    {
        saveSolutionsBeforeExit(null);
        System.out.println("Exiting...");
        System.exit(1);
    }

    @Override
    public void setMazeGenerator(Maze3dGenerator mazeGenerator)
    {
        this.mazeGenerator = mazeGenerator;
    }

    @Override
    public void setSearcher(Searcher searcher)
    {
        this.searcher = searcher;
    }


    public Solution getSolutionByMaze(Maze3d maze)
    {
        if (mazeAndSolution.containsKey(maze))
            return mazeAndSolution.get(maze);
        else
            return null;
    }

    @Override
    public void setProperties(String filePath)
    {
        System.out.println("in model prop");
        setChanged();
        XMLDecoder xmlDecoder = null;
        try
        {
            xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Properties properties = (Properties) xmlDecoder.readObject();

        xmlDecoder.close();

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
            setMazeGenerator(generatorsFactory.get(generator));

            setSearcher(searcherFactory.get(searcherName));
        }
        if (viewProp.equals("CLI"))
        {
            notification.apply();
            notifyObservers(notification);
        }
    }
}