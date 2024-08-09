public void read(final File poifsFile) throws IOException {
        try (NPOIFSFileSystem poifs = new NPOIFSFileSystem(poifsFile, true)) {
            read(poifs);
        }
    }