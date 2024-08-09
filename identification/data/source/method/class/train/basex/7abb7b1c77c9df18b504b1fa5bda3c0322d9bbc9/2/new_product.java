private Str dirName(final QueryContext ctx) throws QueryException {
    String path = checkFile(0, ctx).getParent();
    return Str.get(dir(path == null ? "." : path));
  }