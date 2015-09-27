package model;

import controller.MazeDoesntExistsException;
import notifications.DirNotification;
import notifications.GenerateMazeNotification;
import notifications.LoadMazeObservableNotification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IModel {
	void loadMaze(LoadMazeObservableNotification loadMazeObservableNotification) throws MazeDoesntExistsException, ExecutionException, InterruptedException;

	void generateMaze(GenerateMazeNotification generateMazeNotification) throws ExecutionException, InterruptedException;

	void dir(DirNotification dirNotification);
}
