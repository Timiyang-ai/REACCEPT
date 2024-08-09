private Item delete(final File path, final QueryContext ctx)
      throws QueryException {

    final boolean rec = optionalBool(1, ctx);
    if(path.exists()) {
      if(rec) recDelete(path);
      else delete(path);
    }
    return null;
  }