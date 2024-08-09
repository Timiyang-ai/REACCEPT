private B64 readBinary(final QueryContext ctx) throws QueryException, IOException {
    final File path = checkFile(0, ctx);
    final long off = expr.length > 1 ? checkItr(expr[1], ctx) : 0;
    long len = expr.length > 2 ? checkItr(expr[2], ctx) : 0;

    if(!path.exists()) throw FILE_NF.get(info, path.getAbsolutePath());
    if(path.isDirectory()) throw FILE_ID.get(info, path.getAbsolutePath());

    // read full file
    if(expr.length == 1) return new B64Stream(new IOFile(path), FILE_IE);

    // read file chunk
    final DataAccess da = new DataAccess(new IOFile(path));
    try {
      final long dlen = da.length();
      if(expr.length == 2) len = dlen - off;
      if(off < 0 || off > dlen || len < 0 || off + len > dlen)
        throw FILE_OOR.get(info, off, off + len);
      da.cursor(off);
      return new B64(da.readBytes((int) len));
    } finally {
      da.close();
    }
  }