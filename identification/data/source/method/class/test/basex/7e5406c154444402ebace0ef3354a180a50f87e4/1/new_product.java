private Expr modify() throws QueryException {
    final Expr e = comparison();
    if(e != null) {
      if(wsConsumeWs(UPDATE)) {
        final int s = scope.open();
        final boolean u = ctx.updating;
        ctx.updating(false);
        final Expr m = check(single(), COPYEXPR);
        scope.close(s);
        ctx.updating = u;
        return new Modify(info(), e, m);
      }
    }
    return e;
  }