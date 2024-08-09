private ValueBuilder eval(final QueryContext ctx) throws QueryException {
    return eval(ctx, checkStr(expr[0], ctx), null);
  }