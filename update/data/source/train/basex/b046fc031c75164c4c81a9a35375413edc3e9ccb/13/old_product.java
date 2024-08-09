private StrStream readText(final QueryContext qc) throws QueryException {
    final Path path = checkPath(0, qc);
    final String enc = checkEncoding(1, FILE_UNKNOWN_ENCODING, qc);
    if(!Files.exists(path)) throw FILE_NOT_FOUND.get(info, path);
    if(Files.isDirectory(path)) throw FILE_IS_DIR.get(info, path);
    return new StrStream(new IOFile(path.toFile()), enc, FILE_IO_ERROR, qc);
  }