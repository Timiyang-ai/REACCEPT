protected synchronized void write(final byte[] bytes, final int offset, final int length)  {
        //System.out.println("write " + count);
        try {
            os.write(bytes, offset, length);
        } catch (final IOException ex) {
            final String msg = "Error writing to stream " + getName();
            throw new AppenderLoggingException(msg, ex);
        }
    }