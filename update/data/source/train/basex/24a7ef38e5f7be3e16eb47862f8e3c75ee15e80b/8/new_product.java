private Str readText(final File path, final QueryContext ctx)
      throws QueryException {

    final String enc = expr.length < 2 ? null : string(checkStr(expr[1], ctx));
    if(!path.exists()) PATHNOTEXISTS.thrw(input, path);
    if(path.isDirectory()) PATHISDIR.thrw(input, path);
    if(enc != null && !Charset.isSupported(enc)) ENCNOTEXISTS.thrw(input, enc);

    try {
      byte[] txt = TextInput.content(new IOFile(path), enc).finish();
      if(contains(txt, '\r')) txt = contains(txt, '\n') ?
          delete(txt, '\r') : replace(txt, '\r', '\n');
      return Str.get(txt);
    } catch(final IOException ex) {
      throw FILEERROR.thrw(input, ex);
    }
  }