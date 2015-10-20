package model;

import algorithms.demo.Maze3dDomain;
import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import notifications.ObservableNotification;

import java.util.concurrent.Callable;

public class SolveMazeCall implements Callable<Solution>
{
    private final String mazeName;
    private final MazeModel model;

    public SolveMazeCall(String mazeName, MazeModel model)
    {
        this.mazeName = mazeName;
        this.model = model;
    }

    @Override
    public Solution call() throws Exception
    {
        Maze3d maze = model.getMazeByName(mazeName);

        Searchable searchable = new Maze3dDomain(maze);

        Searcher searcher = model.getSearcher();

        Solution solution = searcher.search(searchable);

        ObservableNotification<Solution> notification = model.<Solution>getNotification();
        notification.setData(solution);

        model.notifyObservers(notification);

        return solution;
    }
}
