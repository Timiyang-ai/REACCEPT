public boolean isRunning(final String id, final InputInfo info) throws QueryException {
    return get(id, info).qp != null;
  }