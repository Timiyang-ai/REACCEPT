private StrStream text(final QueryContext qc) throws QueryException {
    final byte[] uri = checkStr(exprs[0], qc);
    final String enc = checkEncoding(1, BXFE_ENCODING, qc);
    return new StrStream(IO.get(Token.string(uri)), enc, BXFE_IO, qc);
  }