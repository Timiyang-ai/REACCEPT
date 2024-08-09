private Str textEntry(final QueryContext ctx) throws QueryException {
    final String enc = expr.length < 3 ? null : string(checkStr(expr[2], ctx));
    final IO io = new IOContent(entry(ctx));
    try {
      return Str.get(new NewlineInput(io).encoding(enc).content());
    } catch(final IOException ex) {
      throw ZIP_FAIL.thrw(info, ex);
    }
  }