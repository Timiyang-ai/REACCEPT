@Override
    public String readLine() throws IOException {
        synchronized (lock) {
            lineNumber++;
            return super.readLine();
        }
    }