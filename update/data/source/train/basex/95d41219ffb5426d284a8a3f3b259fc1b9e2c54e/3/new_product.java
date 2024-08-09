private Str decodeString(final QueryContext ctx) throws QueryException {
    final B64 b64 = b64(expr[0], true, ctx);
    final String enc = encoding(1, BIN_UE_X, ctx);
    final Long off = expr.length > 2 ? checkItr(expr[2], ctx) : null;
    final Long len = expr.length > 3 ? checkItr(expr[3], ctx) : null;
    if(b64 == null) return null;

    byte[] bytes = b64.binary(info);
    final int bl = bytes.length;
    final int[] bounds = bounds(off, len, bl);

    if(bounds[0] > 0 || bounds[1] < bl) {
      final byte[] tmp = new byte[bounds[1]];
      System.arraycopy(bytes, bounds[0], tmp, 0, bounds[1]);
      bytes = tmp;
    }

    try {
      return Str.get(FNConvert.toString(new IOContent(bytes).inputStream(), enc, true));
    } catch(final IOException ex) {
      throw BIN_CE.get(info, ex);
    }
  }