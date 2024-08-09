private B64 readBinary(final QueryContext qc) throws QueryException, IOException {
    final Path path = checkPath(0, qc);
    final long off = exprs.length > 1 ? checkItr(exprs[1], qc) : 0;
    long len = exprs.length > 2 ? checkItr(exprs[2], qc) : 0;

    if(!Files.exists(path)) throw FILE_NOT_FOUND.get(info, path);
    if(Files.isDirectory(path)) throw FILE_IS_DIR.get(info, path);

    // read full file
    if(exprs.length == 1) return new B64Stream(new IOFile(path.toFile()), FILE_IO_ERROR);

    // read file chunk
    try(final DataAccess da = new DataAccess(new IOFile(path.toFile()))) {
      final long dlen = da.length();
      if(exprs.length == 2) len = dlen - off;
      if(off < 0 || off > dlen || len < 0 || off + len > dlen)
        throw FILE_OUT_OF_RANGE.get(info, off, off + len);
      da.cursor(off);
      return new B64(da.readBytes((int) len));
    }
  }