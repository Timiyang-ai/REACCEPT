private Item lookup(final QueryContext qc, final InputInfo ii) throws QueryException {
    final QNm name = toQNm(exprs[0], qc, sc, false);
    final long arity = toLong(exprs[1], qc);
    if(arity < 0 || arity > Integer.MAX_VALUE) throw FUNCUNKNOWN_X.get(ii, name);

    try {
      final Expr lit = Functions.getLiteral(name, (int) arity, qc, sc, ii);
      return lit == null ? null : lit.item(qc, ii);
    } catch(final QueryException e) {
      // function not found (in most cases: XPST0017)
      return null;
    }
  }