public static void setString(String key, String value) {
        LOCAL_SETTINGS.get().props.setProperty(key, value);
        LOGGER.debug("Setting: {}='{}'", key, value);
    }