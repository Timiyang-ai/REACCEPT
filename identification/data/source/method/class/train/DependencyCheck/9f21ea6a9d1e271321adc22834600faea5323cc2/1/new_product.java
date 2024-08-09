public Directory getDirectory() throws IOException {
        File path = getDataDirectory();
        Directory dir = FSDirectory.open(path);

        return dir;
    }