private FElem parse(final QueryContext ctx) throws QueryException {
    final Item opt = expr.length > 1 ? expr[1].item(ctx, info) : null;
    final TokenMap map = new FuncParams(Q_OPTIONS, info).parse(opt);

    final boolean header = map.contains(HEADER) && eq(map.get(HEADER), TRUE);
    byte sep = CsvParser.SEPMAPPINGS[0];
    final byte[] s = map.get(SEPARATOR);
    if(s != null) {
      if(s.length != 1) BXCS_SEP.thrw(info);
      sep = s[0];
    }
    final CsvParser parser = new CsvParser(sep, header);

    try {
      return parser.convert(checkStr(expr[0], ctx));
    } catch(final IOException ex) {
      throw BXCS_ERROR.thrw(info, ex);
    }
  }