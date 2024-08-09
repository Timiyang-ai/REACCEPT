public void write(OutputStream out) throws IOException {
        try (NPOIFSFileSystem fs = new NPOIFSFileSystem()) {
            write(fs);
            fs.writeFilesystem(out);
        }
    }