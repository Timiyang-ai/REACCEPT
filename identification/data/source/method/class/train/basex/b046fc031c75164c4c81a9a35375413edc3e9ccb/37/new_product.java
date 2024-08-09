private Str textEntry(final QueryContext qc) throws QueryException {
    final String enc = exprs.length < 3 ? null : string(toToken(exprs[2], qc));
    final IO io = new IOContent(entry(qc));
    final boolean val = qc.context.options.get(MainOptions.CHECKSTRINGS);
    try {
      return Str.get(new NewlineInput(io).encoding(enc).validate(val).content());
    } catch(final IOException ex) {
      throw ZIP_FAIL_X.get(info, ex);
    }
  }