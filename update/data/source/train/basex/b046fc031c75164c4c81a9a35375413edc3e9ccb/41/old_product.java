private synchronized Item writeBinary(final boolean append, final QueryContext qc)
      throws QueryException, IOException {

    final Path path = checkParentDir(checkPath(0, qc));
    final Bin bin = checkBin(exprs[1], qc);
    final long off = exprs.length > 2 ? checkItr(exprs[2], qc) : 0;

    // write full file
    if(exprs.length == 2) {
      try(final BufferOutput out = new BufferOutput(new FileOutputStream(path.toFile(), append));
          final InputStream is = bin.input(info)) {
        for(int i; (i = is.read()) != -1;)  out.write(i);
      }
    } else {
      // write file chunk
      try(final RandomAccessFile raf = new RandomAccessFile(path.toFile(), "rw")) {
        final long dlen = raf.length();
        if(off < 0 || off > dlen) throw FILE_OUT_OF_RANGE.get(info, off, dlen);
        raf.seek(off);
        raf.write(bin.binary(info));
      }
    }
    return null;
  }