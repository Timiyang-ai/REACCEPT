public static IO get(final String source) {
    if(source == null) return new IOContent(Token.EMPTY);
    final String s = source.trim();
    if(s.startsWith("<"))      return new IOContent(Token.token(s));
    if(s.startsWith(FILEPREF)) return new IOFile(IOUrl.file(s));
    if(IOFile.valid(s))        return new IOFile(s);
    return new IOUrl(s);
  }