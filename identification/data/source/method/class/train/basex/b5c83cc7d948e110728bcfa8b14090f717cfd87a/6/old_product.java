private StrStream readText(final QueryContext ctx) throws QueryException {
    final File path = checkFile(0, ctx);
    final String enc = encoding(1, FILE_ENCODING, ctx);
    if(!path.exists()) throw FILE_WHICH.get(info, path.getAbsolutePath());
    if(path.isDirectory()) throw FILE_DIR.get(info, path.getAbsolutePath());
    return new StrStream(new IOFile(path), enc, FILE_IO, ctx);
  }