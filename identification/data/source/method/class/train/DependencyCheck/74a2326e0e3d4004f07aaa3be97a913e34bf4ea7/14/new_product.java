public boolean getBoolean(String key) throws InvalidSettingException {
        return Boolean.parseBoolean(getString(key));
    }