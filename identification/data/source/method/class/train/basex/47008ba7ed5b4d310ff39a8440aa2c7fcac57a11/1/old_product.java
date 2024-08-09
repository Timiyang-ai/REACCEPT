final StrStream text(final QueryContext qc) throws QueryException {
    final Path path = toPath(0, qc);
    final String enc = toEncoding(1, FILE_UNKNOWN_ENCODING_X, qc);
    final boolean val = exprs.length < 3 || !toBoolean(exprs[2], qc);
    if(!Files.exists(path)) throw FILE_NOT_FOUND_X.get(info, path.toAbsolutePath());
    if(Files.isDirectory(path)) throw FILE_IS_DIR_X.get(info, path.toAbsolutePath());
    return new StrStream(new IOFile(path.toFile()), enc, FILE_IO_ERROR_X, val);
  }