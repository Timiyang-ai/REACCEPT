private FElem parse(final QueryContext ctx) throws QueryException {
    final byte[] input = checkStr(expr[0], ctx);
    final CsvOptions opts = checkOptions(1, Q_OPTIONS, new CsvOptions(), ctx);

    try {
      return new CsvConverter(opts).convert(input);
    } catch(final QueryIOException ex) {
      throw ex.getCause(info);
    } catch(final IOException ex) {
      throw BXCS_PARSE.thrw(info, ex);
    }
  }