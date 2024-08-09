private ValueBuilder invoke(final QueryContext qc) throws QueryException {
    checkCreate(qc);
    final IO io = checkPath(exprs[0], qc);
    try {
      return eval(qc, io.read(), io.path(), false);
    } catch(final IOException ex) {
      throw IOERR.get(info, ex);
    }
  }