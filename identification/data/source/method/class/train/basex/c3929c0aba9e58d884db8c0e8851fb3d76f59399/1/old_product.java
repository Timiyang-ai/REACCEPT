private FDoc parse(final QueryContext ctx) throws QueryException {
    final byte[] input = checkStr(expr[0], ctx);
    final CsvParserOptions opts = checkOptions(1, Q_OPTIONS, new CsvParserOptions(), ctx);

    try {
      return CsvConverter.convert(input, opts);
    } catch(final QueryIOException ex) {
      throw ex.getCause(info);
    } catch(final IOException ex) {
      throw BXCS_PARSE.thrw(info, ex);
    }
  }