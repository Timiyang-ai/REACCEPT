private Value sortWith(final QueryContext ctx) throws QueryException {
    final Value v = expr[1].value(ctx);
    final Comparator<Item> cmp = getComp(0, ctx);
    if(v.size() < 2) return v;
    final ValueBuilder vb = v.cache();
    try {
      Arrays.sort(vb.item, 0, (int) vb.size(), cmp);
    } catch(final QueryRTException err) {
      throw err.wrapped();
    }
    return vb.value();
  }