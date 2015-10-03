package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import boot.GlobalThreadPool;
import io.MyCompressorOutputStream;
import notifications.DisplayMazeExistsNotification;
import notifications.PropertiesNotification;
import presenter.Properties;

import java.beans.XMLDecoder;
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
	private Maze3dGenerator mazeGenerator;
	private Searcher searcher;


	@Override
	public Maze3d getMazeByName(String mazeName) {
		return mazeAndName.get(mazeName);
	}

	@Override
	public Solution getSolutionByName(String mazeName) {

		return mazeAndSolution.get(mazeName);
	}

	public Searcher getSearcher() {
		return searcher;
	}

	public Maze3dGenerator getMazeGenerator() {
		return mazeGenerator;
	}

	@Override
	public void generateMaze(String mazeName, Integer dimension, Integer rows, Integer columns) throws Exception {
		this.setChanged();

		if(mazeAndName.containsKey(mazeName)) {

			DisplayMazeExistsNotification displayMazeExistsNotification = new DisplayMazeExistsNotification();

			notifyObservers(displayMazeExistsNotification);

		}
		else
		{
			GenerateMazeCall generateMazeCall = new GenerateMazeCall(mazeName, dimension, rows, columns, this);

			Future<Maze3d> future = GlobalThreadPool.getInstance().addCallableToPool(generateMazeCall);

			Maze3d maze = future.get();

			mazeAndName.put(mazeName, maze);

		}
	}

	@Override
	public void displayMaze(String mazeName) throws Exception {

		setChanged();

		DisplayMazeCall displayMazeCall = new DisplayMazeCall(mazeName, this);

		Future<Object> future = GlobalThreadPool.getInstance().addCallableToPool(displayMazeCall);

		future.get();
	}

	@Override
	public void getCrossSelectionBy(String axis, String mazeName, Integer index) throws Exception {
		setChanged();
		if (mazeAndName.containsKey(mazeName)) {
			Maze3d maze = getMazeByName(mazeName);

			DisplayCrossSelectionCall displayCrossSelectionCall = new DisplayCrossSelectionCall(this, axis, maze, index);

			Future<Object> future = GlobalThreadPool.getInstance().addCallableToPool(displayCrossSelectionCall);

			future.get();
		}
	}

	@Override
	public void save(String filePath, String mazeName) throws Exception {
		setChanged();
		if(mazeAndName.containsKey(mazeName)) {
			SaveMazeCall saveMazeCall = new SaveMazeCall(new MyCompressorOutputStream(new FileOutputStream(filePath)), mazeName, this);

			Future<Object> future = GlobalThreadPool.getInstance().addCallableToPool(saveMazeCall);

			future.get();

		}
	}

	@Override
	public void load(String filePath, String mazeName) throws Exception {

		setChanged();
		LoadMazeCall loadMazeCall = new LoadMazeCall(this,filePath,mazeName);

		Future<Object> future = GlobalThreadPool.getInstance().addCallableToPool(loadMazeCall);

		future.get();
	}

	@Override
	public void putMazeAndName(String mazeName, Maze3d maze) {
		mazeAndName.put(mazeName,maze);
	}

	@Override
	public void solve(String name) throws Exception {

		setChanged();

		if(mazeAndName.containsKey(name))
		{
			if(mazeAndSolution.containsKey(mazeAndName.get(name)))
			{
				DisplayMazeExistsNotification displayMazeExistsNotification = new DisplayMazeExistsNotification();

				notifyObservers(displayMazeExistsNotification);
			}
			else {
				SolveMazeCall solveMazeCall = new SolveMazeCall(name,this);

				Future<Solution> future = GlobalThreadPool.getInstance().addCallableToPool(solveMazeCall);

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

		Future<Object> future = GlobalThreadPool.getInstance().addCallableToPool(displaySolutionCall);

		future.get();


	}

	@Override
	public void fileSize(String mazeName) throws Exception {

		setChanged();

		if(mazeAndName.containsKey(mazeName))
		{
			FileSizeCall fileSizeCall = new FileSizeCall(this,mazeName);

			Future<Object> future = GlobalThreadPool.getInstance().addCallableToPool(fileSizeCall);

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

		System.out.println("Enter path(.gz) to load from solutions:");
		filePath = scanner.next();

		ObjectInputStream in = new ObjectInputStream(new GZIPInputStream((new FileInputStream(filePath))));

		HashMap<Maze3d,Solution> hm = new HashMap<Maze3d,Solution>();

		hm = (HashMap<Maze3d, Solution>) in.readObject();

		return hm;

	}

	@Override
	public void setMazeAndSolutionMap(HashMap<Maze3d, Solution> hm) {
		this.mazeAndSolution = hm;
	}

	@Override
	public void exit() throws IOException {
		saveSolutionsBeforeExit();
		System.out.println("Exiting...");
		System.exit(1);
	}

	@Override
	public void setMazeGenerator(Maze3dGenerator mazeGenerator) {
		this.mazeGenerator = mazeGenerator;
	}

	@Override
	public void setSearcher(Searcher searcher) {
		this.searcher = searcher;
	}



	public Solution getSolutionByMaze(Maze3d maze)
	{
		if(mazeAndSolution.containsKey(maze))
			return mazeAndSolution.get(maze);
		else
			return null;
	}

	@Override
	public void setProperties(String filePath) {
		this.setChanged();
		XMLDecoder xmlDecoder = null;
		try {
			xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties properties = (Properties)xmlDecoder.readObject();

		xmlDecoder.close();

		PropertiesNotification propertiesNotification = new PropertiesNotification(properties);

		notifyObservers(propertiesNotification);
	}
}

