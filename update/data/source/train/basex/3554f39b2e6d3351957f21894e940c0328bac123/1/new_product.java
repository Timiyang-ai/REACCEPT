private Str textEntry(final QueryContext ctx) throws QueryException {
    final String enc = expr.length < 3 ? null : string(checkStr(expr[2], ctx));
    final IO io = new IOContent(entry(ctx));
    try {
      return Str.get(TextInput.content(io, enc).finish());
    } catch(final IOException ex) {
      throw ZIPFAIL.thrw(input, ex.getMessage());
    }
  }