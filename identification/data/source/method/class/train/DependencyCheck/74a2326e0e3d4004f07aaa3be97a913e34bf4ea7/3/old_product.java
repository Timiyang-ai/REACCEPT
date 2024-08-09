public static int getInt(String key, int defaultValue) {
        int value;
        try {
            value = Integer.parseInt(Settings.getString(key));
        } catch (NumberFormatException ex) {
            if (!Settings.getString(key, "").isEmpty()) {
                LOGGER.debug("Could not convert property '{}={}' to an int; using {} instead.", key, Settings.getString(key), defaultValue);
            }
            value = defaultValue;
        }
        return value;
    }