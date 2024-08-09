public Directory getDirectory() throws IOException {
        String fileName = Settings.getString(Settings.KEYS.CPE_INDEX);
        File path = new File(fileName);
        Directory dir = FSDirectory.open(path);

        return dir;
    }