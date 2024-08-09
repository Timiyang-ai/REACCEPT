private synchronized Item delete(final QueryContext ctx) throws QueryException, IOException {
    final Path path = checkPath(0, ctx);
    if(optionalBool(1, ctx)) {
      delete(path);
    } else {
      Files.delete(path);
    }
    return null;
  }