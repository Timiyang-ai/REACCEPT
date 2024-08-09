public void setStringIfNotNull(String key, String value) {
        if (null != value) {
            setString(key, value);
        }
    }