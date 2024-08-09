private FElem parse(final QueryContext ctx) throws QueryException {
    final byte[] input = checkStr(expr[0], ctx);
    final Item opt = expr.length > 1 ? expr[1].item(ctx, info) : null;
    final TokenMap map = new FuncParams(Q_OPTIONS, info).parse(opt);

    try {
      return new CsvConverter(options(map)).convert(input);
    } catch(final IOException ex) {
      throw BXCS_PARSE.thrw(info, ex);
    }
  }