private B64 readBinary(final QueryContext qc) throws QueryException, IOException {
    final Path path = toPath(0, qc);
    final long off = exprs.length > 1 ? toLong(exprs[1], qc) : 0;
    long len = exprs.length > 2 ? toLong(exprs[2], qc) : 0;

    if(!Files.exists(path)) throw FILE_NOT_FOUND_X.get(info, path);
    if(Files.isDirectory(path)) throw FILE_IS_DIR_X.get(info, path);

    // read full file
    if(exprs.length == 1) return new B64Stream(new IOFile(path.toFile()), FILE_IO_ERROR_X);

    // read file chunk
    try(final DataAccess da = new DataAccess(new IOFile(path.toFile()))) {
      final long dlen = da.length();
      if(exprs.length == 2) len = dlen - off;
      if(off < 0 || off > dlen || len < 0 || off + len > dlen)
        throw FILE_OUT_OF_RANGE_X_X.get(info, off, off + len);
      da.cursor(off);
      return new B64(da.readBytes((int) len));
    }
  }