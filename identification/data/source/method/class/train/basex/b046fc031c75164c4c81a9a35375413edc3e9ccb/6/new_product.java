private Item close(final QueryContext qc) throws QueryException {
    try {
      session(qc, true).close();
      return null;
    } catch(final IOException ex) {
      throw BXCL_COMMAND_X.get(info, ex);
    }
  }