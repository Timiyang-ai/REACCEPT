private Str resolvePath(final QueryContext ctx) throws QueryException {
    final File path = checkFile(0, ctx);
    final File abs = path.getAbsoluteFile();
    return Str.get(abs.isDirectory() ? dir(abs.getPath()) : abs.getPath());
  }