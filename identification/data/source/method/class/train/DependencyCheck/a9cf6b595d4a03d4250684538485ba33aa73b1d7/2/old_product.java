public static Directory getDirectory() throws IOException {
        String fileName = Settings.getString(Settings.KEYS.CPE_INDEX);
        File path = new File(fileName);
        Directory directory = FSDirectory.open(path);

        return directory;
    }