@Override
    public int available() throws IOException {
        // BEGIN android-changed
        if (closed) {
            throw new IOException(Messages.getString("archive.1E")); //$NON-NLS-1$
        }
        if (currentEntry == null) {
            return 1;
        }
        return super.available();
        // END android-changed
    }