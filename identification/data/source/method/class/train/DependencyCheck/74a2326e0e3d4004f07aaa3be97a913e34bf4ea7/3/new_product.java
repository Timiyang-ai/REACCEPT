public int getInt(String key, int defaultValue) {
        int value;
        try {
            value = Integer.parseInt(getString(key));
        } catch (NumberFormatException ex) {
            if (!getString(key, "").isEmpty()) {
                LOGGER.debug("Could not convert property '{}={}' to an int; using {} instead.", key, getString(key), defaultValue);
            }
            value = defaultValue;
        }
        return value;
    }