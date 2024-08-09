private FElem parse(final QueryContext ctx) throws QueryException {
    final byte[] input = checkStr(expr[0], ctx);
    final Item opt = expr.length > 1 ? expr[1].item(ctx, info) : null;
    final TokenMap map = new FuncParams(Q_OPTIONS, info).parse(opt);

    final boolean header = map.contains(HEADER) && Util.yes(string(map.get(HEADER)));
    int sep = ',';
    final byte[] sp = map.get(SEPARATOR);
    if(sp != null) {
      final TokenParser tp = new TokenParser(sp);
      sep = tp.next();
      if(sep == -1 || tp.next() != -1) BXCS_CONFIG.thrw(info);
    }
    try {
      return new CsvParser(sep, header).convert(input);
    } catch(final IOException ex) {
      throw BXCS_PARSE.thrw(info, ex);
    }
  }