private Item optimize(final QueryContext ctx) throws QueryException {
    final Data data = checkWrite(data(ctx), ctx);
    final boolean all = expr.length == 2 && checkBln(expr[1], ctx);
    ctx.updates.add(new DBOptimize(data, ctx.context, all, info), ctx);
    return null;
  }