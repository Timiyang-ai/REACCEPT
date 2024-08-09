private B64 readBinary(final QueryContext ctx) throws QueryException, IOException {
    final Path path = checkPath(0, ctx);
    final long off = expr.length > 1 ? checkItr(expr[1], ctx) : 0;
    long len = expr.length > 2 ? checkItr(expr[2], ctx) : 0;

    if(!Files.exists(path)) throw FILE_NOT_FOUND.get(info, path);
    if(Files.isDirectory(path)) throw FILE_IS_DIR.get(info, path);

    // read full file
    if(expr.length == 1) return new B64Stream(new IOFile(path.toFile()), FILE_IO_ERROR);

    // read file chunk
    try(final DataAccess da = new DataAccess(new IOFile(path.toFile()))) {
      final long dlen = da.length();
      if(expr.length == 2) len = dlen - off;
      if(off < 0 || off > dlen || len < 0 || off + len > dlen)
        throw FILE_OUT_OF_RANGE.get(info, off, off + len);
      da.cursor(off);
      return new B64(da.readBytes((int) len));
    }
  }