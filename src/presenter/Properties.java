package presenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Properties implements Serializable {

    private List<String> propertiesList = new ArrayList<String>();

    public void setPropertiesList(List<String> propertiesList) {
        this.propertiesList = propertiesList;
    }

    public List<String> getPropertiesList() {
        return propertiesList;
    }

}
