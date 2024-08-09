public static boolean getBoolean(String key) throws InvalidSettingException {
        boolean value;
        try {
            value = Boolean.parseBoolean(Settings.getString(key));
        } catch (NumberFormatException ex) {
            throw new InvalidSettingException("Could not convert property '" + key + "' to an int.", ex);
        }
        return value;
    }