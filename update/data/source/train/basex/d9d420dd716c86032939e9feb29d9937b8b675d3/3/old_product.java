private Value foldLeft1(final QueryContext ctx) throws QueryException {
    final FItem f = withArity(0, 2, ctx);
    final Iter xs = expr[1].iter(ctx);

    Value sum = checkNoEmpty(xs.next());
    for(Item x; (x = xs.next()) != null;) sum = f.invValue(ctx, info, sum, x);
    return sum;
  }