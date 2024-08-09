private B64Stream contentBinary(final QueryContext ctx) throws QueryException {
    final byte[] uri = checkStr(expr[0], ctx);
    return new B64Stream(IO.get(Token.string(uri)), BXFE_IO);
  }