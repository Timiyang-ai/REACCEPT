public static String getString(String key) {
        return System.getProperty(key, LOCAL_SETTINGS.get().props.getProperty(key));
    }