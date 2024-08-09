public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(Settings.getString(key));
    }