private StrStream text(final QueryContext ctx) throws QueryException {
    final byte[] uri = checkStr(expr[0], ctx);
    final String enc = encoding(1, BXFE_ENCODING, ctx);
    return new StrStream(IO.get(Token.string(uri)), enc, BXFE_IO);
  }