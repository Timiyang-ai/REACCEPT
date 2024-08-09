@Override
    public int available() throws IOException {
        // BEGIN android-changed
        if (closed) {
            throw new IOException(Messages.getString("archive.1E")); //$NON-NLS-1$
        }
        return (currentEntry == null || inRead < currentEntry.size) ? 1 : 0;
        // END android-changed
    }