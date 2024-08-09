private synchronized Item writeTextLines(final boolean append, final QueryContext qc)
      throws QueryException, IOException {

    final Path path = checkParentDir(checkPath(0, qc));
    final Iter ir = exprs[1].iter(qc);
    final String enc = encoding(2, FILE_UNKNOWN_ENCODING, qc);
    final Charset cs = enc == null || enc == UTF8 ? null : Charset.forName(enc);

    try(final PrintOutput out = PrintOutput.get(new FileOutputStream(path.toFile(), append))) {
      for(Item it; (it = ir.next()) != null;) {
        if(!it.type.isStringOrUntyped()) throw Err.typeError(this, it, AtomType.STR);
        final byte[] s = it.string(info);
        out.write(cs == null ? s : string(s).getBytes(cs));
        out.write(cs == null ? NL : Prop.NL.getBytes(cs));
      }
    }
    return null;
  }