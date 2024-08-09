private boolean insert(final byte[] string, final int offset, final int rem) {
    final int ts = size(), al = string.length;
    final byte[] tmp = new byte[offset + al + ts - rem];
    System.arraycopy(text, 0, tmp, 0, offset);
    System.arraycopy(string, 0, tmp, offset, al);
    System.arraycopy(text, rem, tmp, offset + al, ts - rem);
    return text(tmp);
  }