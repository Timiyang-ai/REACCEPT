private Str decodeString(final QueryContext qc) throws QueryException {
    final B64 b64 = toB64(exprs[0], qc, true);
    final String enc = toEncoding(1, BIN_UE_X, qc);
    final Long off = exprs.length > 2 ? toLong(exprs[2], qc) : null;
    final Long len = exprs.length > 3 ? toLong(exprs[3], qc) : null;
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
      throw BIN_CE_X.get(info, ex);
    }
  }