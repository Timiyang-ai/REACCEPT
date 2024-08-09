private Iter eval(final QueryContext ctx, final byte[] qu)
      throws QueryException {
    final QueryContext qt = new QueryContext(ctx.resource.context);
    qt.parse(string(qu));
    qt.compile();
    return ItemIter.get(qt.iter());
  }