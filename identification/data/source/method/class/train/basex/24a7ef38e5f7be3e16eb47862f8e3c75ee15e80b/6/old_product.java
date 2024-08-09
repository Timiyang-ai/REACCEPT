private Str read(final File path, final QueryContext ctx)
      throws QueryException {

    final String enc = expr.length < 2 ? null : string(checkStr(expr[1], ctx));
    if(!path.exists()) PATHNOTEXISTS.thrw(input, path);
    if(path.isDirectory()) PATHISDIR.thrw(input, path);

    if(enc != null && !Charset.isSupported(enc)) ENCNOTEXISTS.thrw(input, enc);

    try {
      return Str.get(TextInput.content(new IOFile(path), enc).finish());
    } catch(final IOException ex) {
      throw FILEERROR.thrw(input, ex);
    }
  }