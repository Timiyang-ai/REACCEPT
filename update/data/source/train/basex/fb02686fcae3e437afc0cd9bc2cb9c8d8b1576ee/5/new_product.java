private Expr type(final QueryContext qc) {
    FnTrace.trace(Util.inf("{ type: %, size: %, exprSize: % }", exprs[0].seqType(), exprs[0].size(),
        exprs[0].exprSize()), token(exprs[0].toString()), qc);
    return exprs[0];
  }