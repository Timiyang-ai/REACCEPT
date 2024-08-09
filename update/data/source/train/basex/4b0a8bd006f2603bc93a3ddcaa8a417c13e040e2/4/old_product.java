private synchronized Item writeText(final boolean append, final QueryContext qc)
      throws QueryException, IOException {

    final Path path = checkParentDir(checkPath(0, qc));
    final byte[] s = checkStr(exprs[1], qc);
    final String enc = encoding(2, FILE_UNKNOWN_ENCODING, qc);
    final Charset cs = enc == null || enc == UTF8 ? null : Charset.forName(enc);

    try(final PrintOutput out = PrintOutput.get(new FileOutputStream(path.toFile(), append))) {
      out.write(cs == null ? s : string(s).getBytes(cs));
    }
    return null;
  }