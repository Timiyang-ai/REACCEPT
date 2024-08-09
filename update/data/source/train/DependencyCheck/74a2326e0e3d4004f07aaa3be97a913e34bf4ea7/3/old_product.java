public static boolean getBoolean(String key) throws InvalidSettingException {
        return Boolean.parseBoolean(Settings.getString(key));
    }