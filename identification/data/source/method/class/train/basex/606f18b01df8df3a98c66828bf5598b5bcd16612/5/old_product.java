private Str parent(final QueryContext ctx) throws QueryException {
    final String parent = checkFile(0, ctx).getAbsoluteFile().getParent();
    return parent == null ? null : Str.get(dir(parent));
  }