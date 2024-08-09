private RestXqError error(final Value value, final QNm name) throws QueryException {
    if(value.size() != 1) throw error(function.info, ANN_EXACTLY, "%", name.string(), 1);

    // name of parameter
    final String err = toString(value.itemAt(0), name);
    final NameTest test;
    QNm qnm = null;
    if(err.equals("*")) {
      test = new NameTest(false);
    } else if(err.startsWith("*:")) {
      final byte[] local = token(err.substring(2));
      if(!XMLToken.isNCName(local)) throw error(INV_CODE, err);
      test = new NameTest(new QNm(local), NameTest.Kind.NAME, false, null);
    } else if(err.endsWith(":*")) {
      final byte[] prefix = token(err.substring(0, err.length() - 2));
      if(!XMLToken.isNCName(prefix)) throw error(INV_CODE, err);
      qnm = new QNm(concat(prefix, COLON), function.sc);
      test = new NameTest(qnm, NameTest.Kind.URI, false, null);
    } else {
      final byte[] nm = token(err);
      if(!XMLToken.isQName(nm)) throw error(INV_CODE, err);
      qnm = new QNm(nm, function.sc);
      test = new NameTest(qnm, NameTest.Kind.URI_NAME, false, null);
    }
    // message
    if(qnm != null && qnm.hasPrefix() && !qnm.hasURI()) throw error(INV_NONS, qnm);

    return new RestXqError(test);
  }