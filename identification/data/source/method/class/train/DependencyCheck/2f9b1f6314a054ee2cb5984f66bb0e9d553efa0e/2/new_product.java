public static long getLong(String key) throws InvalidSettingException {
        long value;
        try {
            value = Long.parseLong(Settings.getString(key));
        } catch (NumberFormatException ex) {
            throw new InvalidSettingException("Could not convert property '" + key + "' to an int.", ex);
        }
        return value;
    }