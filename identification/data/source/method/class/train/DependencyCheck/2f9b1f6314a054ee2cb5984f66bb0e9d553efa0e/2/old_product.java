public static long getLong(String key) {
        return Long.parseLong(Settings.getString(key));
    }