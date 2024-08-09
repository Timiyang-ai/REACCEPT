private RestXqError error(final Value value, final QNm name) throws QueryException {
    if(value.size() != 1) throw error(function.info, ANN_EXACTLY, "%", name.string(), 1);

    // name of parameter
    final String err = toString(value.itemAt(0), name);
    QNm code = null;
    if(!"*".equals(err)) {
      final byte[] c = token(err);
      if(!XMLToken.isQName(c)) throw error(INV_CODE, c);
      code = new QNm(c, function.sc);
      if(!code.hasURI() && code.hasPrefix()) throw error(INV_NONS, code);
    }
    // message
    return new RestXqError(code);
  }