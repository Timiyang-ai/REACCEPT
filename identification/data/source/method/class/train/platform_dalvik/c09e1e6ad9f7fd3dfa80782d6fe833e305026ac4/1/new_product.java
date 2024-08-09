@Override
    public int read(byte[] buffer, int off, int nbytes) throws IOException {
        if (closed) {
            throw new IOException(Messages.getString("archive.1E")); //$NON-NLS-1$
        }
        // BEGIN android-changed
        if (eos) {
            return -1;
        }
        // avoid int overflow, check null buffer
        if (off > buffer.length || nbytes < 0 || off < 0
                || buffer.length - off < nbytes) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int bytesRead;
        try {
            bytesRead = super.read(buffer, off, nbytes);
        } finally {
            eos = eof; // update eos after every read(), even when it throws
        }

        if (bytesRead != -1) {
            crc.update(buffer, off, bytesRead);
        }

        if (eos) {
            verifyCrc();
        }

        return bytesRead;
        // END android-changed
    }