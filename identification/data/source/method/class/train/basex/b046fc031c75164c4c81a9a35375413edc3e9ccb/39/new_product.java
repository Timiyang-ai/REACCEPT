private B64Stream binary(final QueryContext qc) throws QueryException {
    final byte[] uri = toToken(exprs[0], qc);
    return new B64Stream(IO.get(Token.string(uri)), BXFE_IO_X);
  }