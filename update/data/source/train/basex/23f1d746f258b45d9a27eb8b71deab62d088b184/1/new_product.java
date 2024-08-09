private StrStream readText(final File path, final QueryContext ctx)
      throws QueryException {

    final String enc = encoding(1, FILE_ENCODING, ctx);
    if(!path.exists()) FILE_WHICH.thrw(info, path.getAbsolutePath());
    if(path.isDirectory()) FILE_DIR.thrw(info, path.getAbsolutePath());
    return new StrStream(new IOFile(path), enc, FILE_IO, ctx);
  }