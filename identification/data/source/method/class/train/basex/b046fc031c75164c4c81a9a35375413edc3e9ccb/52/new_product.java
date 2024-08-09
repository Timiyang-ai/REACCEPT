private synchronized Item writeText(final boolean append, final QueryContext qc)
      throws QueryException, IOException {

    final Path path = checkParentDir(toPath(0, qc));
    final byte[] s = toToken(exprs[1], qc);
    final String enc = toEncoding(2, FILE_UNKNOWN_ENCODING_X, qc);
    final Charset cs = enc == null || enc == UTF8 ? null : Charset.forName(enc);

    try(final PrintOutput out = PrintOutput.get(new FileOutputStream(path.toFile(), append))) {
      out.write(cs == null ? s : string(s).getBytes(cs));
    }
    return null;
  }