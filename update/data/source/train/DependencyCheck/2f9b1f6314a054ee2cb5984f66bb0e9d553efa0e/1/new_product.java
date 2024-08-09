public static int getInt(String key) throws InvalidSettingException {
        int value;
        try {
            value = Integer.parseInt(Settings.getString(key));
        } catch (NumberFormatException ex) {
            throw new InvalidSettingException("Could not convert property '" + key + "' to an int.", ex);
        }
        return value;
    }