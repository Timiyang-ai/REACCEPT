private Item writeBinary(final File path, final QueryContext ctx)
      throws QueryException {

    final B64 b64 = (B64) checkType(expr[1].item(ctx, input), AtomType.B64);
    final boolean append = optionalBool(2, ctx);
    if(path.isDirectory()) PATHISDIR.thrw(input, path);

    try {
      final FileOutputStream out = new FileOutputStream(path, append);
      try {
        out.write(b64.toJava());
      } finally {
        out.close();
      }
    } catch(final IOException ex) {
      FILEERROR.thrw(input, ex);
    }
    return null;
  }