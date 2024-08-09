private boolean insert(final byte[] string, final int offset, final int rem) {
    final int ts = size();
    final ByteList bl = new ByteList(offset + string.length + ts - rem);
    bl.add(text, 0, offset).add(string).add(text, rem, ts);
    return text(bl.finish());
  }