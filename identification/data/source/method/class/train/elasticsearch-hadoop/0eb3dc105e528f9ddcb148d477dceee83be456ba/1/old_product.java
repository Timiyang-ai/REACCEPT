public int read(byte b[], int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        }
        else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }
        if (pos >= count) {
            return -1;
        }
        if (pos + len > count) {
            len = count - pos;
        }
        if (len <= 0) {
            return 0;
        }
        System.arraycopy(b, off, data.bytes, data.size, len);
        pos += len;
        return len;
    }