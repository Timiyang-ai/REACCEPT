private Str textEntry(final QueryContext qc) throws QueryException {
    final String enc = exprs.length < 3 ? null : string(checkStr(exprs[2], qc));
    final IO io = new IOContent(entry(qc));
    final boolean val = qc.context.options.get(MainOptions.CHECKSTRINGS);
    try {
      return Str.get(new NewlineInput(io).encoding(enc).validate(val).content());
    } catch(final IOException ex) {
      throw ZIP_FAIL.get(info, ex);
    }
  }