private Expr function(final QNm name, final Expr... exprs) throws QueryException {
    final InputInfo ii = info();
    final ExprList argList = new ExprList().add(exprs);
    final int[] holes = argumentList(argList, name.string());
    final Expr[] args = argList.finish();
    alter = FUNCUNKNOWN_X;
    alterFunc = name;
    alterPos = pos;

    final Expr ret;
    if(holes != null) {
      final int card = args.length + holes.length;
      final Expr lit = Functions.getLiteral(name, card, qc, sc, ii, false);
      final Expr f = lit != null ? lit : unknownLit(name, card, ii);
      ret = new PartFunc(sc, ii, f, args, holes);
      if(lit != null && (lit instanceof XQFunctionExpr ? ((XQFunctionExpr) f).annotations() :
        ((FuncLit) lit).annotations()).contains(Annotation.UPDATING)) qc.updating();
    } else {
      final TypedFunc f = Functions.get(name, args, qc, sc, ii);
      if(f == null) {
        ret = null;
      } else {
        if(f.anns.contains(Annotation.UPDATING)) qc.updating();
        ret = f.fun;
      }
    }

    if(ret != null) alter = null;
    return ret;
  }