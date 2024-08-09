private Iter foldRight(final QueryContext ctx) throws QueryException {
    final FItem f = withArity(0, 2, ctx);
    final Value xs = ctx.value(expr[2]);
    // evaluate start value lazily if it's passed straight through
    if(xs.isEmpty()) return expr[1].iter(ctx);

    Value res = ctx.value(expr[1]);
    for(long i = xs.size(); --i >= 0;)
      res = f.invValue(ctx, info, xs.itemAt(i), res);

    return res.iter();
  }