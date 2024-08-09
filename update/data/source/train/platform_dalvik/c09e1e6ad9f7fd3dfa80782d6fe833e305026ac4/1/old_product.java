@Override
    public int read(byte[] buffer, int off, int nbytes) throws IOException {
        if (closed) {
            throw new IOException(Messages.getString("archive.1E")); //$NON-NLS-1$
        }
        if (eof) {
            return -1;
        }
        // avoid int overflow, check null buffer
        if (off <= buffer.length && nbytes >= 0 && off >= 0
                && buffer.length - off >= nbytes) {
            int val = super.read(buffer, off, nbytes);
            if (val != -1) {
                crc.update(buffer, off, val);
            } else if (!eos) {
                eos = true;
                // Get non-compressed bytes read by fill
                int size = inf.getRemaining();
                final int trailerSize = 8; // crc (4 bytes) + total out (4
                // bytes)
                byte[] b = new byte[trailerSize];
                int copySize = (size > trailerSize) ? trailerSize : size;

                System.arraycopy(buf, len - size, b, 0, copySize);
                readFully(b, copySize, trailerSize - copySize);

                if (getLong(b, 0) != crc.getValue()) {
                    throw new IOException(Messages.getString("archive.20")); //$NON-NLS-1$
                }
                if ((int) getLong(b, 4) != inf.getTotalOut()) {
                    throw new IOException(Messages.getString("archive.21")); //$NON-NLS-1$
                }
            }
            return val;
        }
        throw new ArrayIndexOutOfBoundsException();
    }