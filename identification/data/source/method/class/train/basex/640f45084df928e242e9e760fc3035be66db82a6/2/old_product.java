private B64Stream readBinary(final QueryContext ctx) throws QueryException {
    final File path = checkFile(0, ctx);
    if(!path.exists()) FILE_WHICH.thrw(info, path.getAbsolutePath());
    if(path.isDirectory()) FILE_DIR.thrw(info, path.getAbsolutePath());
    return new B64Stream(new IOFile(path), FILE_IO);
  }