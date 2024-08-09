private int[] match(final Matcher m, final String str, final FElem par,
      final int g) {

    final FElem nd = new FElem(g == 0 ? MATCH : MGROUP);
    if(g > 0) nd.add(new FAttr(NR, token(g)));

    final int start = m.start(g), end = m.end(g), gc = m.groupCount();
    int[] pos = { g + 1, start }; // group and position in string
    while(pos[0] <= gc && m.end(pos[0]) <= end) {
      final int st = m.start(pos[0]);
      if(st >= 0) { // group matched
        if(pos[1] < st) nd.add(new FTxt(token(str.substring(pos[1], st))));
        pos = match(m, str, nd, pos[0]);
      } else pos[0]++; // skip it
    }
    if(pos[1] < end) {
      nd.add(new FTxt(token(str.substring(pos[1], end))));
      pos[1] = end;
    }
    par.add(nd);
    return pos;
  }