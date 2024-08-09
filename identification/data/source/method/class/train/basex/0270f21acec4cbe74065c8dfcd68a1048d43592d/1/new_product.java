public boolean finished(final String id, final InputInfo info) throws QueryException {
    return get(id, info).qp == null;
  }