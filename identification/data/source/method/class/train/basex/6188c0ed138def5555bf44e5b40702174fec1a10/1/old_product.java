private void functionDecl(final boolean up, final Ann ann)
      throws QueryException {

    skipWS();

    final QNm name = eQName(FUNCNAME, ctx.sc.nsFunc);
    if(keyword(name)) error(RESERVED, name);
    if(module != null && !eq(name.uri(), module.uri())) error(MODNS, name);

    wsCheck(PAR1);
    final VarStack vl = ctx.vars.cache(4);
    final Var[] args = paramList();
    wsCheck(PAR2);

    final UserFunc func = new UserFunc(input(), name, args, optAsType(), ann, true);
    func.updating = up;

    ctx.funcs.add(func, input());
    if(!wsConsumeWs(EXTERNAL)) func.expr = enclosed(NOFUNBODY);
    ctx.vars.reset(vl);
  }