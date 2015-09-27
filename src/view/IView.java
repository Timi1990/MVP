package view;

import java.util.List;

public interface IView {
    void onMazeLoaded(String mazeName);

    void onMazeGenerated(String mazeName);

    void displayDir(List<String> fileNames);
	
}
