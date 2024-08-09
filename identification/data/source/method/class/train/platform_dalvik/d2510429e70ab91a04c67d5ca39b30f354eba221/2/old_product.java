public final ByteBuffer get(byte[] dest, int off, int len) {
        int length = dest.length;
        if ((off < 0 ) || (len < 0) || (long)off + (long)len > length) {
            throw new IndexOutOfBoundsException();
        }
        if (len > remaining()) {
            throw new BufferUnderflowException();
        }
        getBaseAddress().getByteArray(offset+position, dest, off, len);
        position += len;
        return this;
    }