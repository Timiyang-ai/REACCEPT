private synchronized Item delete(final QueryContext ctx) throws QueryException {
    final File path = checkFile(0, ctx);
    if(!path.exists()) throw FILE_WHICH.get(info, path.getAbsolutePath());
    if(optionalBool(1, ctx)) {
      deleteRec(path);
    } else if(!path.delete()) {
      throw (path.isDirectory() ? FILE_NEDIR : FILE_DEL).get(info, path);
    }
    return null;
  }