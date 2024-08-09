private Int find(final QueryContext ctx) throws QueryException {
    final B64 b64 = b64(expr[0], true, ctx);
    final Long off = checkItr(expr[1], ctx);
    final B64 srch = b64(expr[2], false, ctx);
    if(b64 == null) return null;

    final byte[] bytes = b64.binary(info);
    final int bl = bytes.length;
    final byte[] search = srch.binary(info);
    final int[] bounds = bounds(off, null, bl);
    final int pos = indexOf(bytes, search, bounds[0]);
    return pos == -1 ? null : Int.get(pos);
  }