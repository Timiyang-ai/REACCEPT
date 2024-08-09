@Override
    public int read() throws IOException {
        synchronized (lock) {
            if (isClosed()) {
                throw new IOException(Msg.getString("K005b")); //$NON-NLS-1$
            }
            /* Are there buffered characters available? */
            if (pos < count || fillbuf() != -1) {
                return buf[pos++];
            }
            markpos = -1;
            return -1;
        }
    }