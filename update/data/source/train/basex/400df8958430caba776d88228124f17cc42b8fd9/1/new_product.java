protected byte[] createUrl(final byte[] url, final XQMap map, final byte[] anchor)
      throws QueryException {

    final TokenBuilder tb = new TokenBuilder().add(url);
    int c = 0;
    for(final Item key : map.keys()) {
      final byte[] name = key.string(info);
      for(final Item value : map.get(key, info)) {
        tb.add(c++ == 0 ? '?' : '&').add(Token.encodeUri(name, false));
        tb.add('=').add(Token.encodeUri(value.string(info), false));
      }
    }
    if(anchor.length > 0) tb.add('#').add(Token.encodeUri(anchor, false));
    return tb.finish();
  }