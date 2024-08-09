@Override
    public synchronized void flush() throws IOException {
        flushInternal();
        out.flush();
    }