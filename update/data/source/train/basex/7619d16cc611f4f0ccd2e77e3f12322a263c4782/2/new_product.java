public static byte[] parse(final byte[] str, final InputInfo ii) throws QueryException {
    if(contains(str, DASHES) || endsWith(str, '-')) throw COMINVALID.get(ii);
    return str;
  }