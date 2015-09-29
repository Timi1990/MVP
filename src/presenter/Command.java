package presenter;

import java.util.List;

/**
 * Created by Timi on 9/28/2015.
 */
public interface Command {

        void doCommand(List<String> args) throws Exception;

}
