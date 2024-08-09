public void write(OutputStream out) throws IOException {
        try (POIFSFileSystem fs = new POIFSFileSystem()) {
            write(fs);
            fs.writeFilesystem(out);
        }
    }