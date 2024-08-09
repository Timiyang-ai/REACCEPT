private synchronized Item writeBinary(final boolean append, final QueryContext ctx)
      throws QueryException, IOException {

    final File path = check(checkFile(0, ctx));
    final Iter ir = expr[1].iter(ctx);
    final BufferOutput out = new BufferOutput(new FileOutputStream(path, append));
    try {
      for(Item it; (it = ir.next()) != null;) {
        if(!(it instanceof Bin)) BINARYTYPE.thrw(info, it.type);
        final InputStream is = it.input(info);
        try {
          for(int i; (i = is.read()) != -1;)  out.write(i);
        } finally {
          is.close();
        }
      }
    } finally {
      out.close();
    }
    return null;
  }