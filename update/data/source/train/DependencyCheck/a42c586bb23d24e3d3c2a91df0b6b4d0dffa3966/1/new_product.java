public static File getFile(String key) throws IOException {
        final String file = getString(key);
        final String baseDir = getString(Settings.KEYS.DATA_DIRECTORY);
        if (baseDir != null) {
            if (baseDir.startsWith("[JAR]/")) {
                final File jarPath = getJarPath();
                final File newBase = new File(jarPath.getCanonicalPath(), baseDir.substring(6));
                return new File(newBase, file);
            }
            return new File(baseDir, file);
        }
        return new File(file);
    }