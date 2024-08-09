final Item parse(final byte[] json, final boolean xml, final QueryContext qc,
      final InputInfo ii) throws QueryException {

    final JsonParserOptions opts = new JsonParserOptions();
    if(exprs.length > 1) {
      final Map options = toMap(exprs[1], qc);
      new FuncOptions(null, info).acceptUnknown().assign(options, opts);
    }

    final boolean esc = opts.get(JsonParserOptions.ESCAPE);
    final FuncItem fb = opts.get(JsonParserOptions.FALLBACK);
    final FItem fallback;
    if(fb == null) {
      fallback = null;
    } else {
      fallback = STRFUNC.cast(fb, qc, sc, ii);
    }
    if(esc && fallback != null) throw JSON_OPT_X.get(ii,
        "Escaping cannot be combined with a fallback function.");

    try {
      opts.set(JsonOptions.FORMAT, xml ? JsonFormat.BASIC : JsonFormat.MAP);
      final JsonConverter conv = JsonConverter.get(opts);
      if(!esc && fallback != null) conv.fallback(new JsonFallback() {
        @Override
        public String convert(final String string) {
          try {
            return Token.string(fallback.invokeItem(qc, ii, Str.get(string)).string(ii));
          } catch(final QueryException ex) {
            throw new QueryRTException(ex);
          }
        }
      });
      return conv.convert(json, null);
    } catch(final QueryRTException ex) {
      throw ex.getCause();
    } catch(final QueryIOException ex) {
      Util.debug(ex);
      final QueryException qe = ex.getCause(info);
      final QueryError error = qe.error();
      final String message = ex.getLocalizedMessage();
      if(error == BXJS_PARSE_X_X_X) throw JSON_PARSE_X.get(ii, message);
      if(error == BXJS_DUPLICATE_X) throw JSON_DUPLICATE_X.get(ii, message);
      if(error == BXJS_INVALID_X) throw JSON_OPT_X.get(ii, message);
      throw qe;
    }
  }