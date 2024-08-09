private ValueBuilder functions(final QueryContext ctx) throws QueryException {
    final ValueBuilder vb = new ValueBuilder();
    for(final StaticFunc sf : ctx.funcs.funcs()) {
      final FuncItem fi = Functions.getUser(sf, ctx, sf.sc, info);
      if(!fi.annotations().contains(Ann.Q_UPDATING)) vb.add(fi);
    }
    return vb;
  }