package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import notifications.ObservableNotification;

import java.io.IOException;
import java.util.HashMap;

public interface IModel
{
    <T> void setNotification(ObservableNotification<T> notification);

    <T> ObservableNotification getNotification();

    void generateMaze(String mazeName, Integer dimension, Integer rows, Integer columns) throws Exception;

    void displayMaze(String mazeName) throws Exception;

    Maze3d getMazeByName(String mazeName);

    Solution getSolutionByName(String mazeName);

    void getCrossSelectionBy(String axis, String mazeName, Integer index) throws Exception;

    void getCrossSelectionBy(Maze3d maze, Integer index, String axis) throws Exception;

    void save(String filePath, String mazeName) throws Exception;

    void load(String filePath, String mazeName) throws Exception;

    void putMazeAndName(String mazeName, Maze3d maze);

    void solve(String name) throws Exception;

    Searcher getAlgorithm();

    void putMazeAndSolution(Maze3d maze, Solution solution);

    void displaySolution(String mazeName) throws Exception;

    void fileSize(String mazeName) throws Exception;

    void saveSolutionsBeforeExit(String path) throws IOException;

    HashMap<Maze3d, Solution> loadSolutionsForMazes() throws IOException, ClassNotFoundException;

    void setMazeAndSolutionMap(HashMap<Maze3d, Solution> hm);

    void exit() throws IOException;

    void setMazeGenerator(Maze3dGenerator mazeGenerator);

    void setSearcher(Searcher searcher);

    void setProperties(String filePath);
}
