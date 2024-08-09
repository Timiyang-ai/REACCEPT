private Item add(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final Data data = data(0, ctx);
    final Item it = checkItem(expr[1], ctx);
    String path = "";
    if(expr.length == 3) {
      path = path(2, ctx);
      if(endsWith(checkStr(expr[2], ctx), '/')) path += '/';
    }

    ctx.updates.add(new DBAdd(data, input, it, path, ctx.context), ctx);
    return null;
  }