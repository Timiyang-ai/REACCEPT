private Item swallow(final QueryContext ctx) throws QueryException {
    final Iter ir = expr[0].iter(ctx);
    while(ir.next() != null);
    return null;
  }