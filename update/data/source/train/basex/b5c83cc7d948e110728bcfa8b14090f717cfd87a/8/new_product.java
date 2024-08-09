private synchronized Item writeText(final boolean append, final QueryContext ctx)
      throws QueryException, IOException {

    final File path = check(checkFile(0, ctx));
    final byte[] s = checkStr(expr[1], ctx);
    final String enc = encoding(2, FILE_UE, ctx);
    final Charset cs = enc == null || enc == UTF8 ? null : Charset.forName(enc);

    final PrintOutput out = PrintOutput.get(new FileOutputStream(path, append));
    try {
      out.write(cs == null ? s : string(s).getBytes(cs));
    } finally {
      out.close();
    }
    return null;
  }