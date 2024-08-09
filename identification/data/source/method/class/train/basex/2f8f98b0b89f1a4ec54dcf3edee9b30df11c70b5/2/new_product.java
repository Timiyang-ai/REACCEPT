private Iter text(final QueryContext ctx) throws QueryException {
    final IndexContext ic = new IndexContext(ctx, data(0, ctx), null, true);
    return new IndexAccess(input, expr[1], IndexType.TEXT, ic).iter(ctx);
  }