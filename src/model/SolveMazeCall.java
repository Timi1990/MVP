package model;

import algorithms.demo.Maze3dDomain;
import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import notifications.SolveMazeNotification;

import java.util.concurrent.Callable;

/**
 * Created by Timi on 9/28/2015.
 */
public class SolveMazeCall implements Callable<Solution> {

    private String mazeName;
    private MazeModel model;

    public SolveMazeCall(String mazeName,MazeModel model) {
        this.mazeName = mazeName;
        this.model = model;
    }


    @Override
    public Solution call() throws Exception {

            Maze3d maze=model.getMazeByName(mazeName);

            Searchable searchable=new Maze3dDomain(maze);

            Searcher searcher = model.getSearcher();
        
            Solution solution = searcher.search(searchable);

            SolveMazeNotification solveMazeNotification = new SolveMazeNotification(searcher.getClass().toString().substring(24),mazeName);

            model.notifyObservers(solveMazeNotification);

            return solution;
    }
}
