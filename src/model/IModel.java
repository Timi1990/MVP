package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;

import java.io.IOException;
import java.util.HashMap;

public interface IModel {

	void generateMaze(String mazeName,Integer dimension, Integer rows, Integer columns) throws Exception;

	void displayMaze(String mazeName) throws Exception;

	Maze3d getMazeByName(String mazeName);

	Solution getSolutionByName(String mazeName);

	void getCrossSelectionBy(String axis, String mazeName, Integer index) throws Exception;

	void save(String filePath, String mazeName) throws Exception;

	void load(String filePath,String mazeName) throws Exception;

	void putMazeAndName(String mazeName, Maze3d maze);

	void solve (String name, String algorithm) throws Exception;

	void putMazeAndSolution(Maze3d maze, Solution solution);

	void displaySolution(String mazeName) throws Exception;

	void fileSize(String mazeName) throws Exception;

	void saveSolutionsBeforeExit() throws IOException;

	HashMap<Maze3d,Solution> loadSolutionsForMazes() throws IOException, ClassNotFoundException;

	void exit() throws IOException;


}
