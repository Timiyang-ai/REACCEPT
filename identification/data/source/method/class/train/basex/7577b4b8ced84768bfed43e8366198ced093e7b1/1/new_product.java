public static Map create(final String s) throws QueryException {
    Map map = EMPTY;
    // 0: key, 1: colon, 2: value, 3: comma
    int state = 0;
    final TokenBuilder key = new TokenBuilder();
    final TokenBuilder val = new TokenBuilder();
    boolean quote = false, error = false;
    final int pl = s.length();
    for(int p = 0; p < pl; p++) {
      final char ch = s.charAt(p);
      if(quote) {
        if(ch == '"' && (p + 1 == pl || s.charAt(p + 1) != '"')) {
          quote = false;
          if(state++ != 2) continue;
          map = map.insert(Str.get(key.finish()), Str.get(val.finish()), null);
          key.reset();
          val.reset();
        } else {
          if(ch == '"') ++p;
          (state == 0 ? key : val).add(ch);
        }
      } else {
        if(ch == ' ') continue;
        quote = ch == '"';
        switch(state) {
          case 0:
          case 2: error = !quote; break;
          case 1: error = ch != ':'; state++; break;
          case 3: error = ch != ','; state = 0; break;
        }
      }
      if(error) INVIN.thrw(null, "'" + ch + "'");
    }
    if(quote || state == 1 || state == 2) INVIN.thrw(null, "end of input");
    return map;
  }