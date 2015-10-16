package notifications;

import presenter.Properties;

public class PropertiesNotification implements ObservableNotification
{
    private final Properties properties;

    public PropertiesNotification(Properties properties)
    {
        this.properties = properties;
    }

    @Override
    public void print()
    {

    }

    public Properties getProperties()
    {
        return properties;
    }
}
