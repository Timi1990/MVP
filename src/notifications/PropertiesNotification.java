package notifications;

import presenter.Properties;

/**
 * Created by Timi on 10/2/2015.
 */
public class PropertiesNotification extends ObservableNotification {

    private Properties properties;
    public PropertiesNotification(Properties properties) {
        super(ObservableNotificationNames.PropertiesName);
        this.properties = properties;
    }

    @Override
    public void print() {

    }

    public Properties getProperties() {
        return properties;
    }
}
