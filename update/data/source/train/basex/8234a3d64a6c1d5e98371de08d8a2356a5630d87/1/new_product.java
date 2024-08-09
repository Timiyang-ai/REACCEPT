private ValueBuilder functions(final QueryContext ctx) throws QueryException {
    final ValueBuilder vb = new ValueBuilder();
    for(final StaticFunc sf : ctx.funcs.funcs()) {
      vb.add(Functions.getUser(sf, ctx, sf.sc, info));
    }
    return vb;
  }