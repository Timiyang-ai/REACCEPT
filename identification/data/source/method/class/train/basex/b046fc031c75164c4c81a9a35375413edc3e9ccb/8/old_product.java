private B64 hash(final String algo, final QueryContext qc) throws QueryException {
    return hashBinary(checkStrBin(exprs[0], qc), algo);
  }