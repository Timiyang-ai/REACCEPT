private synchronized Item writeTextLines(final boolean append, final QueryContext qc)
      throws QueryException, IOException {

    final Path path = checkParentDir(toPath(0, qc));
    final Value value = qc.value(exprs[1]);
    final String enc = toEncoding(2, FILE_UNKNOWN_ENCODING_X, qc);
    final Charset cs = enc == null || enc == UTF8 ? null : Charset.forName(enc);

    try(final PrintOutput out = PrintOutput.get(new FileOutputStream(path.toFile(), append))) {
      for(final Item it : value) {
        if(!it.type.isStringOrUntyped()) throw castError(info, it, AtomType.STR);
        final byte[] s = it.string(info);
        out.write(cs == null ? s : string(s).getBytes(cs));
        out.write(cs == null ? NL : Prop.NL.getBytes(cs));
      }
    }
    return null;
  }