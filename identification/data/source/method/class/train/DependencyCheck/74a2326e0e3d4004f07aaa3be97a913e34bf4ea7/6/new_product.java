public String getString(String key) {
        return System.getProperty(key, props.getProperty(key));
    }