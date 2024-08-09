public long getLong(String key) throws InvalidSettingException {
        try {
            return Long.parseLong(getString(key));
        } catch (NumberFormatException ex) {
            throw new InvalidSettingException("Could not convert property '" + key + "' to a long.", ex);
        }
    }