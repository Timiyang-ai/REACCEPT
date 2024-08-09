private Value binaryToBytes(final QueryContext qc) throws QueryException {
    try {
      return BytSeq.get(checkItem(exprs[0], qc).input(info).content());
    } catch(final IOException ex) {
      throw BXCO_STRING.get(info, ex);
    }
  }