package boot;

import model.MazeModel;
import presenter.Presenter;
import view.MazeMenu;

import java.io.IOException;

public class Run
{

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {


        //		Scanner scanner = new Scanner(System.in);
        //
        //		System.out.println("Enter input file path...");
        //		String fileInput = scanner.next();
        //
        //		System.out.println("Enter output file path...");
        //		String fileOutput = scanner.next();
        //
        //		MazeModel model = new MazeModel();
        //		MazeView view = new MazeView();
        //
        //		Properties properties = readPropertiesFromXML();  //Default in file.
        //		Presenter presenter = new Presenter(model,view);
        //
        //
        //		model.addObserver(presenter);
        //		view.addObserver(presenter);
        //		presenter.setProperties(properties);
        //
        //
        //
        //		HashMap<String, Command> commandHashMap = presenter.createAndGetCommandHashMap();

        //		view.start(fileInput, fileOutput, commandHashMap);

        MazeModel mazeModel = new MazeModel();
        MazeMenu mazeMenu = new MazeMenu(500, 500);
        Presenter presenter = new Presenter(mazeModel, mazeMenu);
        mazeModel.addObserver(presenter);
        mazeMenu.addObserver(presenter);

        mazeMenu.run();

        //		C:\Users\Timi\Desktop\input.txt
        //		C:\Users\Timi\Desktop\output2.gz


    }

    //	public Properties readPropertiesFromXML(String filePath)
    //	{
    //
    //		XMLDecoder xmlDecoder = null;
    //		try {
    //			xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
    //		} catch (FileNotFoundException e) {
    //			e.printStackTrace();
    //		}
    //
    //		Properties properties = (Properties)xmlDecoder.readObject();
    //
    //		xmlDecoder.close();
    //
    //		return properties;
    //	}
}


