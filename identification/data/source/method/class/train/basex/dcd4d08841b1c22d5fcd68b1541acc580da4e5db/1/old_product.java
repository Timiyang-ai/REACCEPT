final ItemList invoke(final QueryContext qc, final boolean updating) throws QueryException {
    checkCreate(qc);
    final IO io = checkPath(0, qc);
    try {
      return eval(qc, string(io.read()), io.url(), updating);
    } catch(final IOException ex) {
      throw IOERR_X.get(info, ex);
    }
  }