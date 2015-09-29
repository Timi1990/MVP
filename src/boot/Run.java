package boot;

import model.MazeModel;
import presenter.Command;
import presenter.Presenter;
import view.MazeView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Run {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter input file path...");
		String fileInput = scanner.next();

		System.out.println("Enter output file path...");
		String fileOutput = scanner.next();

		MazeModel model = new MazeModel();
		MazeView view = new MazeView();
		Presenter presenter = new Presenter(model,view);

		model.addObserver(presenter);
		view.addObserver(presenter);

		HashMap<String, Command> commandHashMap = presenter.createAndGetCommandHashMap();

		view.start(fileInput, fileOutput, commandHashMap);

//		C:\Users\Timi\Desktop\input.txt
//		C:\Users\Timi\Desktop\output2.gz

	}
}


