private synchronized Item writeTextLines(final boolean append, final QueryContext ctx)
      throws QueryException, IOException {

    final File path = check(checkFile(0, ctx));
    final Iter ir = expr[1].iter(ctx);
    final String enc = encoding(2, FILE_UE, ctx);
    final Charset cs = enc == null || enc == UTF8 ? null : Charset.forName(enc);

    try(final PrintOutput out = PrintOutput.get(new FileOutputStream(path, append))) {
      for(Item it; (it = ir.next()) != null;) {
        if(!it.type.isStringOrUntyped()) throw Err.typeError(this, AtomType.STR, it);
        final byte[] s = it.string(info);
        out.write(cs == null ? s : string(s).getBytes(cs));
        out.write(cs == null ? NL : Prop.NL.getBytes(cs));
      }
    }
    return null;
  }