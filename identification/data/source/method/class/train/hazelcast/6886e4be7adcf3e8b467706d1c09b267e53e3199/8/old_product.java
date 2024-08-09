public String getString(HazelcastProperty property) {
        String configValue = properties.getProperty(property.getName());
        if (configValue != null) {
            return configValue;
        }
        String systemProperty = property.getSystemProperty();
        if (systemProperty != null) {
            return systemProperty;
        }

        HazelcastProperty parent = property.getParent();
        if (parent != null) {
            return getString(parent);
        }

        return property.getDefaultValue();
    }