private StrStream text(final QueryContext qc) throws QueryException {
    final byte[] uri = toToken(exprs[0], qc);
    final String enc = toEncoding(1, BXFE_ENCODING_X, qc);
    return new StrStream(IO.get(Token.string(uri)), enc, BXFE_IO_X, qc);
  }