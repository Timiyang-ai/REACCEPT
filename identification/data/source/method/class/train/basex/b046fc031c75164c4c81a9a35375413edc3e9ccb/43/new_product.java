private StrStream readText(final QueryContext qc) throws QueryException {
    final Path path = toPath(0, qc);
    final String enc = toEncoding(1, FILE_UNKNOWN_ENCODING_X, qc);
    if(!Files.exists(path)) throw FILE_NOT_FOUND_X.get(info, path);
    if(Files.isDirectory(path)) throw FILE_IS_DIR_X.get(info, path);
    return new StrStream(new IOFile(path.toFile()), enc, FILE_IO_ERROR_X, qc);
  }