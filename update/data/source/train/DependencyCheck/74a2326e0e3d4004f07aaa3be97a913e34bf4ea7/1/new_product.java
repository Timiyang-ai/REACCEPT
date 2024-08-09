public void setString(String key, String value) {
        props.setProperty(key, value);
        LOGGER.debug("Setting: {}='{}'", key, value);
    }