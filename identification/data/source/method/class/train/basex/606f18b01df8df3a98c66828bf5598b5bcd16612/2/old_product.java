private synchronized Item writeBinary(final boolean append, final QueryContext ctx)
      throws QueryException, IOException {

    final File path = check(checkFile(0, ctx));
    final Bin bin = checkBinary(expr[1], ctx);
    final long off = expr.length > 2 ? checkItr(expr[2], ctx) : 0;

    // write full file
    if(expr.length == 2) {
      try(final BufferOutput out = new BufferOutput(new FileOutputStream(path, append));
          final InputStream is = bin.input(info)) {
        for(int i; (i = is.read()) != -1;)  out.write(i);
      }
    } else {
      // write file chunk
      try(final RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
        final long dlen = raf.length();
        if(off < 0 || off > dlen) throw FILE_OOR.get(info, off, dlen);
        raf.seek(off);
        raf.write(bin.binary(info));
      }
    }
    return null;
  }