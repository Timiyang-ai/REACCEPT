private Item parse(final QueryContext ctx) throws QueryException {
    final byte[] input = checkStr(expr[0], ctx);
    final JsonOptions opts = checkOptions(1, Q_OPTIONS, new JsonOptions(), ctx);

    try {
      return JsonConverter.get(opts, info).convert(string(input)).item(ctx, info);
    } catch(final QueryIOException ex) {
      throw ex.getCause();
    }
  }