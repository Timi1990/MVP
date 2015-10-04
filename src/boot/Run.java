package boot;

import model.MazeModel;
import presenter.Command;
import presenter.Presenter;
import view.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Run {

	public static void main(String[] args) {

		MazeModel mazeModel = new MazeModel();
		MazeCLIView mazeCLIView = new MazeCLIView();
		MazeGUIView mazeGUIView = new MazeGUIView(500,500);
		Presenter presenter = new Presenter(mazeModel, mazeGUIView);
		mazeModel.addObserver(presenter);
		mazeGUIView.addObserver(presenter);

		mazeGUIView.run();

		mazeCLIView.addObserver(presenter);
		presenter.setView(mazeCLIView);

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter input file path...");
		String fileInput = scanner.next();

		System.out.println("Enter output file path...");
		String fileOutput = scanner.next();

		HashMap<String, Command> commandHashMap = presenter.createAndGetCommandHashMap();

		mazeCLIView.start(fileInput, fileOutput, commandHashMap);


//		C:\Users\Timi\Desktop\input.txt
//		C:\Users\Timi\Desktop\output2.gz


	}
}


