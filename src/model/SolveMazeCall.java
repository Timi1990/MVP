package model;

import algorithms.demo.Maze3dDomain;
import algorithms.mazeGenerators.Maze3d;
import algorithms.search.*;
import notifications.SolveMazeNotification;

import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Created by Timi on 9/28/2015.
 */
public class SolveMazeCall implements Callable<Solution> {

    private String mazeName;
    private String algorithm;
    private MazeModel model;

    public SolveMazeCall(String mazeName, String algorithm,MazeModel model) {
        this.mazeName = mazeName;
        this.algorithm = algorithm;
        this.model = model;
    }


    @Override
    public Solution call() throws Exception {

        HashMap<String,Searcher> algorithmFactory=new HashMap<String,Searcher>();
        algorithmFactory.put("BFS", new BFS());
        algorithmFactory.put("AStar", new Astar(new MazeManhattanDistance()));

        if(algorithmFactory.containsKey(algorithm)){

            Maze3d maze=model.getMazeByName(mazeName);

            Searchable searchable=new Maze3dDomain(maze);

            Solution solution=algorithmFactory.get(algorithm).search(searchable);

            SolveMazeNotification solveMazeNotification = new SolveMazeNotification(algorithm,mazeName);

            model.notifyObservers(solveMazeNotification);

            return solution;
        }
        else
            System.err.println("algorithm doesn't exists...");

        return null;
    }
}
