private Str pathToNative(final QueryContext ctx) throws QueryException, IOException {
    final Path nat = checkPath(0, ctx).toRealPath();
    return get(nat, Files.isDirectory(nat));
  }