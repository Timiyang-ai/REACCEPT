private FElem parse(final QueryContext ctx) throws QueryException {
    final byte[] input = checkStr(expr[0], ctx);
    final CsvOptions opts = new CsvOptions();
    if(expr.length > 1) new FuncOptions(Q_OPTIONS, info).parse(expr[1].item(ctx, info), opts);

    try {
      return new CsvConverter(opts).convert(input);
    } catch(final IOException ex) {
      throw BXCS_PARSE.thrw(info, ex);
    }
  }