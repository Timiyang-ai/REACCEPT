private Int integerFromBase(final QueryContext qc, final InputInfo ii) throws QueryException {
    final byte[] str = toToken(exprs[0], qc);
    final long base = toLong(exprs[1], qc);
    if(base < 2 || base > 36) throw INVBASE_X.get(ii, base);

    long res = 0;
    for(final byte b : str) {
      final int num = b <= '9' ? b - 0x30 : (b & 0xDF) - 0x37;
      if(!(b >= '0' && b <= '9' || b >= 'a' && b <= 'z' ||
          b >= 'A' && b <= 'Z') || num >= base)
        throw INVBASEDIG_X_X.get(ii, base, (char) (b & 0xff));

      res = res * base + num;
    }

    return Int.get(res);
  }