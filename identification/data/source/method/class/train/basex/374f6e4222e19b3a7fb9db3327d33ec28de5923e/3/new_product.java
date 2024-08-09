private Expr delete() throws QueryException {
    final int p = pos;
    if(!wsConsumeWs(DELETE) || !wsConsumeWs(NODES) && !wsConsumeWs(NODE)) {
      pos = p;
      return null;
    }
    qc.updating();
    return new Delete(sc, info(), check(single(), INCOMPLETE));
  }