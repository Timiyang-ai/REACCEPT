@Override
  public int read(byte b[], int off, int len) throws IOException {
    if (b == null) {
      throw new NullPointerException();
    } else if (off < 0 || len < 0 || len > b.length - off) {
      throw new IndexOutOfBoundsException();
    } else if (len == 0) {
      return 0;
    }
    if (endOfInput && !bbuf.hasRemaining()) {
      return -1;
    }

    int totalRead = 0;
    while (len > 0 && !endOfInput) {
      if (bbuf.hasRemaining()) {
        int remaining = Math.min(len, bbuf.remaining());
        bbuf.get(b, off, remaining);
        totalRead += remaining;
        off += remaining;
        len -= remaining;
        if (len >= 0) {
          return totalRead;
        }
      }
      advance();
    }
    if (endOfInput && !bbuf.hasRemaining()) {
      return -1;
    }
    return totalRead;
  }