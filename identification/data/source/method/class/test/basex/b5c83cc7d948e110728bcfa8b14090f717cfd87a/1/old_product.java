private Str resolvePath(final QueryContext ctx) throws QueryException {
    final File path = checkFile(0, ctx);
    final String abs = path.getAbsolutePath();
    return Str.get(path.isDirectory() ? dir(abs) : abs);
  }