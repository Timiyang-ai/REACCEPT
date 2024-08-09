@Override
    public String tagKey(String key) {
        String conventionKey = delegate.tagKey(key);

        conventionKey = conventionKey.replaceAll("^_", "").replaceAll("^sf_", ""); // 2

        if (!conventionKey.matches("^[a-zA-Z].*")) { // 3
            conventionKey = "a" + conventionKey;
        }

        if (conventionKey.length() > 128) {
            conventionKey = conventionKey.substring(0, 128); // 1
        }

        return conventionKey;
    }