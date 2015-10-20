package presenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Properties implements Serializable
{
    private final List<String> propertiesList = new ArrayList<String>();

    public List<String> getPropertiesList()
    {
        return propertiesList;
    }

}
