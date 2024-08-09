public static IO get(final String source) {
    if(source == null) return new IOContent(Token.EMPTY);
    final String s = source.trim();
    if(s.startsWith(FILEPREF)) return new IOFile(IOUrl.file(s));
    if(s.startsWith("<"))      return new IOContent(Token.token(s));
    if(!s.contains("://"))     return new IOFile(s);
    return new IOUrl(s);
  }