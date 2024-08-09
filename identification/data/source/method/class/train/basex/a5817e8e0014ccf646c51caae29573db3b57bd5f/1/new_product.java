private Str name(final QueryContext ctx) throws QueryException {
    final Path path = checkPath(0, ctx).getFileName();
    return path == null ? Str.ZERO : Str.get(path.toString());
  }