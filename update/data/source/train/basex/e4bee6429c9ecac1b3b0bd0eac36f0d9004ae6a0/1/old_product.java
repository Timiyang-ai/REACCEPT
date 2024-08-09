private B64 shift(final QueryContext ctx) throws QueryException {
    final B64 b64 = b64(expr[0], true, ctx);
    long by = checkItr(expr[1], ctx);
    if(b64 == null) return null;

    final byte[] bytes = b64.binary(info);
    final int bl = bytes.length;

    final byte[] tmp = new byte[bl];
    int r = 0;
    if(by > 0) {
      for(int i = bl - 1; i >= 0; i--) {
        final byte b = bytes[i];
        tmp[i] = (byte) (b << by | r);
        r = b >>> 32 - by;
      }
    } else {
      by = -by;
      for(int i = 0; i < bl; i++) {
        final byte b = bytes[i];
        tmp[i] = (byte) (b >>> by | r);
        r = b << 32 - by;
      }
    }
    return new B64(tmp);
  }