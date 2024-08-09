private Iter search(final QueryContext ctx) throws QueryException {
    final Data data = checkData(ctx);
    final Value terms = ctx.value(expr[1]);
    final Item opt = expr.length > 2 ? expr[2].item(ctx, info) : null;
    final FTOptions opts = new FTOptions();
    new FuncOptions(Q_OPTIONS, info).parse(opt, opts);
    return search(data, terms, opts, this, ctx);
  }