@Override
    public synchronized void close() throws IOException {
        if (null != in) {
            super.close();
            in = null;
        }
        buf = null;
        closed = true;
    }