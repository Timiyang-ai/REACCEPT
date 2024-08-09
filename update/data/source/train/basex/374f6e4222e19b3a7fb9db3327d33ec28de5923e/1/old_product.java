private Expr delete() throws QueryException {
    final int i = pos;
    if(!wsConsumeWs(DELETE) || !wsConsumeWs(NODES) && !wsConsumeWs(NODE)) {
      pos = i;
      return null;
    }
    qc.updating();
    return new Delete(sc, info(), check(single(), INCOMPLETE));
  }