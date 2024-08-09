private Str parent(final QueryContext ctx) throws QueryException {
    final Path parent = absolute(checkPath(0, ctx)).getParent();
    return parent == null ? null : get(parent, true);
  }