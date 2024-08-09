private RestXqError error(final Value value, final QNm name) throws QueryException {
    if(value.size() != 1) throw error(function.info, ANN_EXACTLY, "%", name.string(), 1);

    // name of parameter
    final String err = toString(value.itemAt(0), name);
    final Kind kind;
    QNm qnm = null;
    if(err.equals("*")) {
      kind = Kind.WILDCARD;
    } else if(err.startsWith("*:")) {
      final byte[] local = token(err.substring(2));
      if(!XMLToken.isNCName(local)) throw error(INV_CODE, err);
      qnm = new QNm(local);
      kind = Kind.NAME;
    } else if(err.endsWith(":*")) {
      final byte[] prefix = token(err.substring(0, err.length() - 2));
      if(!XMLToken.isNCName(prefix)) throw error(INV_CODE, err);
      qnm = new QNm(concat(prefix, COLON), function.sc);
      kind = Kind.URI;
    } else {
      final Matcher m = EQNAME.matcher(err);
      if(m.matches()) {
        final byte[] uri = token(m.group(1));
        final byte[] local = token(m.group(2));
        if(local.length == 1 && local[0] == '*') {
          qnm = new QNm(COLON, uri);
          kind = Kind.URI;
        } else {
          if(!XMLToken.isNCName(local) || !Uri.uri(uri).isValid()) throw error(INV_CODE, err);
          qnm = new QNm(local, uri);
          kind = Kind.URI_NAME;
        }
      } else {
        final byte[] nm = token(err);
        if(!XMLToken.isQName(nm)) throw error(INV_CODE, err);
        qnm = new QNm(nm, function.sc);
        kind = Kind.URI_NAME;
      }
    }
    // message
    if(qnm != null && qnm.hasPrefix() && !qnm.hasURI()) throw error(INV_NONS, qnm);

    return new RestXqError(new NameTest(qnm, kind, false, null));
  }