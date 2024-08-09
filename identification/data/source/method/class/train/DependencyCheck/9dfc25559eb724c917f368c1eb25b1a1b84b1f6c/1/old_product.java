public static File getFile(String key) {
        final String file = getString(key);
        final String baseDir = getString(Settings.KEYS.DATA_DIRECTORY);
        if (baseDir != null) {
            if (baseDir.startsWith("[JAR]/")) {
                final File jarPath = getJarPath();
                final File newBase = new File(jarPath, baseDir.substring(6));
                if (Settings.KEYS.DATA_DIRECTORY.equals(key)) {
                    return newBase;
                }
                return new File(newBase, file);
            }
            if (Settings.KEYS.DATA_DIRECTORY.equals(key)) {
                return new File(baseDir);
            }
            return new File(baseDir, file);
        }
        return new File(file);
    }