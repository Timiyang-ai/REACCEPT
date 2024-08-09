private Item writeBinary(final File path, final QueryContext ctx,
      final boolean append) throws QueryException {

    if(path.isDirectory()) PATHISDIR.thrw(input, path);

    try {
      final FileOutputStream out = new FileOutputStream(path, append);
      try {
        final Iter ir = expr[1].iter(ctx);
        for(Item it; (it = ir.next()) != null;) {
          out.write(((B64) checkType(it, AtomType.B64)).toJava());
        }
      } finally {
        out.close();
      }
    } catch(final IOException ex) {
      FILEERROR.thrw(input, ex);
    }
    return null;
  }