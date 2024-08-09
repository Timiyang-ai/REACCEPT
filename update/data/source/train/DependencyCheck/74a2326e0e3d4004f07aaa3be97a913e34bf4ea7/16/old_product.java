public static long getLong(String key) throws InvalidSettingException {
        try {
            return Long.parseLong(Settings.getString(key));
        } catch (NumberFormatException ex) {
            throw new InvalidSettingException("Could not convert property '" + key + "' to a long.", ex);
        }
    }