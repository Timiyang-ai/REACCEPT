private Item add(final QueryContext ctx) throws QueryException {
    final Data data = checkData(ctx);
    final byte[] path = expr.length < 3 ? Token.EMPTY : token(path(2, ctx));
    final NewInput input = checkInput(checkItem(expr[1], ctx), path);
    ctx.updates.add(new DBAdd(data, input, ctx, info), ctx);
    return null;
  }