public static File getFile(String key) {
        final String baseDir = getString(Settings.KEYS.DATA_DIRECTORY);
        final String tmp = getString(key);
        if (baseDir != null) {
            return new File(baseDir, tmp);
        }
        return new File(tmp);
    }