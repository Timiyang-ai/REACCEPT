public void setStringIfNotEmpty(String key, String value) {
        if (null != value && !value.isEmpty()) {
            setString(key, value);
        }
    }