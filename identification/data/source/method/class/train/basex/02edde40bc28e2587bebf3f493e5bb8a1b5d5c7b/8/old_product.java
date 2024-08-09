private Item optimize(final QueryContext ctx) throws QueryException {
    final Data data = checkWrite(checkData(ctx), ctx);
    final boolean all = expr.length > 1 && checkBln(expr[1], ctx);

    final Item opt = expr.length > 2 ? expr[2].item(ctx, info) : null;
    final TokenMap map = new FuncParams(Q_OPTIONS, info).parse(opt);
    // check database options
    ctx.updates.add(new DBOptimize(data, ctx, all, map, info), ctx);
    return null;
  }