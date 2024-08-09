private B64 hash(final String algo, final QueryContext qc) throws QueryException {
    return hashBinary(toBinary(exprs[0], qc), algo);
  }