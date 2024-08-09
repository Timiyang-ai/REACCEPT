public void read(final File poifsFile) throws IOException {
        try (POIFSFileSystem poifs = new POIFSFileSystem(poifsFile, true)) {
            read(poifs);
        }
    }