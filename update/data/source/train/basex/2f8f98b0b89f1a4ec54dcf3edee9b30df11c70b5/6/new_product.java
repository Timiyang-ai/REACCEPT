private Item delete(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final Data data = data(0, ctx);
    final byte[] target = path(checkStr(expr[1], ctx));
    for(final int pre : data.doc(string(target))) {
      ctx.updates.add(new DeleteNode(pre, data, input), ctx);
    }
    return null;
  }