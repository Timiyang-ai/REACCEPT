private Item delete(final QueryContext ctx) throws QueryException {
    final Data data = ctx.resource.data(checkStr(expr[0], ctx), input);
    final String path = string(checkStr(expr[1], ctx));

    final int[] docs = data.doc(path);
    for(final int pre : docs)
      ctx.updates.add(new DeleteNode(pre, data, input), ctx);

    return null;
  }