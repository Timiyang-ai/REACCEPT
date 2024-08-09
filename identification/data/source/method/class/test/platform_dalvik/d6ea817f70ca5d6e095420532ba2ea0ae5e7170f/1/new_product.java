@Override
    public void close() throws IOException {
        buf = null;
        InputStream localIn = in;
        in = null;
        if (localIn != null) {
            localIn.close();
        }
    }