@Override
    public int read() throws IOException {
        synchronized (lock) {
            if (isClosed()) {
                throw new IOException(Msg.getString("K005b")); //$NON-NLS-1$
            }
            /* Are there buffered characters available? */
            if (pos < end || fillBuf() != -1) {
                return buf[pos++];
            }
            return -1;
        }
    }