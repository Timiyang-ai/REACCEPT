@Override
    public synchronized void flush() throws IOException {
        if (count > 0) {
            out.write(buf, 0, count);
        }
        count = 0;
        out.flush();
    }