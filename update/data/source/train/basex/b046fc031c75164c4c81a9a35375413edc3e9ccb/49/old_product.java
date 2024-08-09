private Item lookup(final QueryContext qc, final InputInfo ii) throws QueryException {
    final QNm name = checkQNm(exprs[0], qc, sc);
    final long arity = checkItr(exprs[1], qc);
    if(arity < 0 || arity > Integer.MAX_VALUE) throw FUNCUNKNOWN.get(ii, name);

    try {
      final Expr lit = Functions.getLiteral(name, (int) arity, qc, sc, ii);
      return lit == null ? null : lit.item(qc, ii);
    } catch(final QueryException e) {
      // function not found (in most cases: XPST0017)
      return null;
    }
  }