private StrStream readText(final QueryContext ctx) throws QueryException {
    final Path path = checkPath(0, ctx);
    final String enc = encoding(1, FILE_UNKNOWN_ENCODING, ctx);
    if(!Files.exists(path)) throw FILE_NOT_FOUND.get(info, path);
    if(Files.isDirectory(path)) throw FILE_IS_DIR.get(info, path);
    return new StrStream(new IOFile(path.toFile()), enc, FILE_IO_ERROR, ctx);
  }