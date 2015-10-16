package presenter;

import java.util.List;

public interface Command
{
    void doCommand(List<String> args) throws Exception;
}
