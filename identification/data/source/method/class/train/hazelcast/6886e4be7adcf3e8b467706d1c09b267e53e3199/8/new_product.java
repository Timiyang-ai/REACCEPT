public String getString(HazelcastProperty property) {
        String value = properties.getProperty(property.getName());
        if (value != null) {
            return value;
        }

        value = property.getSystemProperty();
        if (value != null) {
            return value;
        }

        HazelcastProperty parent = property.getParent();
        if (parent != null) {
            return getString(parent);
        }

        String deprecatedName = property.getDeprecatedName();
        if (deprecatedName != null) {
            value = get(deprecatedName);
            if (value == null) {
                value = System.getProperty(deprecatedName);
            }

            if (value != null) {
                // we don't have a logger available, and the Logging service is constructed after the Properties are created.
                System.err.print("Don't use deprecated '" + deprecatedName + "' "
                        + "but use '" + property.getName() + "' instead. "
                        + "The former name will be removed in the next Hazelcast release.");

                return value;
            }
        }

        return property.getDefaultValue();
    }