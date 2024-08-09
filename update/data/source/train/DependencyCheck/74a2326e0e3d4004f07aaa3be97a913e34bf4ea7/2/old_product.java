public static synchronized File getTempDirectory() throws IOException {
        if (tempDirectory == null) {
            final File baseTemp = new File(Settings.getString(Settings.KEYS.TEMP_DIRECTORY, System.getProperty("java.io.tmpdir")));
            tempDirectory = FileUtils.createTempDirectory(baseTemp);
        }
        return tempDirectory;
    }