package notifications;

/**
 * Created by Timi on 9/27/2015.
 */
public class GenerateMazeNotification extends ObservableNotification {

    String mazeName;
    Integer dimension,rows,columns;

    public GenerateMazeNotification(String mazeName,Integer dimension,Integer rows,Integer columns ) {
        super(ObservableNotificationNames.GenerateMazeNotificationName);
        this.mazeName = mazeName;
        this.dimension = dimension;
        this.rows = rows;
        this.columns = columns;
    }

    public String getMazeName() {
        return mazeName;
    }

    public int getDimension() {
        return dimension;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public void print() {
        System.out.println("Maze "+mazeName+" is ready");
    }
}
