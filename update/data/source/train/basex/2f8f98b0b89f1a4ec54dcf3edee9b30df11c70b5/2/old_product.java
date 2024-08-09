private Iter text(final QueryContext ctx) throws QueryException {
    final Data data = ctx.resource.data(checkStr(expr[0], ctx), input);
    final IndexContext ic = new IndexContext(ctx, data, null, true);
    return new IndexAccess(input, expr[1], IndexType.TEXT, ic).iter(ctx);
  }