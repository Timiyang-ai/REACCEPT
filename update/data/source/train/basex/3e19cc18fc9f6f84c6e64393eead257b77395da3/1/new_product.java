private B64 hash(final String algo, final QueryContext ctx) throws QueryException {
    return hashBinary(checkStrBin(checkItem(expr[0], ctx)), algo);
  }