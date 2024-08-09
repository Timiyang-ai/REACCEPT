public static File getFile(String key) {
        final String file = getString(key);
        return new File(file);
    }