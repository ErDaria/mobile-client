package settings;

import properties.LaunchPropertyProvider;

public class ParameterAccessor {
    private static final LaunchPropertyProvider LAUNCH_PROPERTY_PROVIDER = new LaunchPropertyProvider();

    public static LaunchPropertyProvider getLaunchPropertyProvider() {return LAUNCH_PROPERTY_PROVIDER;}
}
