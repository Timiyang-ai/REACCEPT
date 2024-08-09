private Value binaryToBytes(final QueryContext qc) throws QueryException {
    try {
      return BytSeq.get(toBin(exprs[0], qc).input(info).content());
    } catch(final IOException ex) {
      throw BXCO_STRING_X.get(info, ex);
    }
  }