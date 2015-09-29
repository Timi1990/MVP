package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import boot.GlobalThreadPool;
import io.MyCompressorOutputStream;
import notifications.DisplayMazeNotification;
import notifications.DisplaySolutionNotification;

import java.io.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MazeModel extends Observable implements IModel {
	private HashMap<Maze3d, Solution> mazeAndSolution = new HashMap<Maze3d, Solution>();
	private HashMap<String, Maze3d> mazeAndName = new HashMap<String, Maze3d>();


	@Override
	public Maze3d getMazeByName(String mazeName) {
		return mazeAndName.get(mazeName);
	}

	@Override
	public Solution getSolutionByName(String mazeName) {

		return mazeAndSolution.get(mazeName);
	}


	@Override
	public void generateMaze(String mazeName, Integer dimension, Integer rows, Integer columns) throws Exception {
		this.setChanged();

		if(mazeAndName.containsKey(mazeName)) {

			DisplayMazeNotification displayMazeNotification = new DisplayMazeNotification();

			displayMazeNotification.setMaze(mazeAndName.get(mazeName));

			notifyObservers(displayMazeNotification);

		}
		else
		{
			GenerateMazeCall generateMazeCall = new GenerateMazeCall(mazeName, dimension, rows, columns, this);

			Future<Maze3d> future = GlobalThreadPool.getInstance().addCallableMazeToPool(generateMazeCall);

			Maze3d maze = future.get();

			mazeAndName.put(mazeName, maze);

		}
	}

	@Override
	public void displayMaze(String mazeName) throws Exception {

		setChanged();

		DisplayMazeCall displayMazeCall = new DisplayMazeCall(mazeName, this);

		Future<Object> future = GlobalThreadPool.getInstance().addAnyCallableToPool(displayMazeCall);

		future.get();
	}

	@Override
	public void getCrossSelectionBy(String axis, String mazeName, Integer index) throws Exception {
		setChanged();
		if (mazeAndName.containsKey(mazeName)) {
			Maze3d maze = getMazeByName(mazeName);

			DisplayCrossSelectionCall displayCrossSelectionCall = new DisplayCrossSelectionCall(this, axis, maze, index);

			Future<Object> future = GlobalThreadPool.getInstance().addAnyCallableToPool(displayCrossSelectionCall);

			future.get();
		}
	}

	@Override
	public void save(String filePath, String mazeName) throws Exception {
		setChanged();
		if(mazeAndName.containsKey(mazeName)) {
			SaveMazeCall saveMazeCall = new SaveMazeCall(new MyCompressorOutputStream(new FileOutputStream(filePath)), mazeName, this);

			Future<Object> future = GlobalThreadPool.getInstance().addAnyCallableToPool(saveMazeCall);

			future.get();

		}
	}

	@Override
	public void load(String filePath, String mazeName) throws Exception {

		setChanged();
		LoadMazeCall loadMazeCall = new LoadMazeCall(this,filePath,mazeName);

		Future<Object> future = GlobalThreadPool.getInstance().addAnyCallableToPool(loadMazeCall);

		future.get();
	}

	@Override
	public void putMazeAndName(String mazeName, Maze3d maze) {
		mazeAndName.put(mazeName,maze);
	}

	@Override
	public void solve(String name, String algorithm) throws Exception {

		setChanged();

		if(mazeAndName.containsKey(name))
		{
			if(mazeAndSolution.containsKey(mazeAndName.get(name)))
			{
				Solution solution = mazeAndSolution.get(mazeAndName.get(name));

				DisplaySolutionNotification displaySolutionNotification = new DisplaySolutionNotification(solution);

				notifyObservers(displaySolutionNotification);
			}
			else {
				SolveMazeCall solveMazeCall = new SolveMazeCall(name, algorithm, this);

				Future<Solution> future = GlobalThreadPool.getInstance().addCallableSolutionToPool(solveMazeCall);

				Solution solution = future.get();

				mazeAndSolution.put(mazeAndName.get(name), solution);
			}
		}

	}

	@Override
	public void putMazeAndSolution(Maze3d maze, Solution solution) {
		mazeAndSolution.put(maze,solution);
	}

	@Override
	public void displaySolution(String mazeName) throws Exception {

		setChanged();

		DisplaySolutionCall displaySolutionCall = new DisplaySolutionCall(this,mazeName);

		Future<Object> future = GlobalThreadPool.getInstance().addAnyCallableToPool(displaySolutionCall);

		future.get();


	}

	@Override
	public void fileSize(String mazeName) throws Exception {

		setChanged();

		if(mazeAndName.containsKey(mazeName))
		{
			FileSizeCall fileSizeCall = new FileSizeCall(this,mazeName);

			Future<Object> future = GlobalThreadPool.getInstance().addAnyCallableToPool(fileSizeCall);

			future.get();
		}
	}

	@Override
	public void saveSolutionsBeforeExit() throws IOException {

		Scanner scanner = new Scanner(System.in);
		String filePath;

		System.out.println("Enter path to save maze's solutions");
		filePath=scanner.next();

		ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));

		out.writeObject(mazeAndSolution);
		out.flush();
		out.close();
	}

	@Override
	public HashMap<Maze3d,Solution> loadSolutionsForMazes() throws IOException, ClassNotFoundException {

		Scanner scanner = new Scanner(System.in);
		String filePath;

		System.out.println("Enter path to load from");
		filePath = scanner.next();

		ObjectInputStream in = new ObjectInputStream(new GZIPInputStream((new FileInputStream(filePath))));

		HashMap<Maze3d,Solution> hm = new HashMap<Maze3d,Solution>();

		hm = (HashMap<Maze3d, Solution>) in.readObject();

		return hm;

	}

	@Override
	public void exit() throws IOException {
		saveSolutionsBeforeExit();
		System.out.println("Exiting...");
		System.exit(1);
	}


	public Solution getSolutionByMaze(Maze3d maze)
	{
		if(mazeAndSolution.containsKey(maze))
			return mazeAndSolution.get(maze);
		else
			return null;
	}
}

