private Item replace(final QueryContext ctx) throws QueryException {
    final Data data = ctx.resource.data(checkStr(expr[0], ctx), input);
    final String path = ACreate.path(string(checkStr(expr[1], ctx)));
    final ANode doc = checkNode(checkItem(expr[2], ctx));

    // collect all old documents
    final int[] old = data.doc(path);
    if(old.length > 0) {
      final int pre = old[0];
      if(old.length > 1 || !eq(data.text(pre, true), token(path)))
        DOCTRGMULT.thrw(input);
      ctx.updates.add(new DeleteNode(pre, data, input), ctx);
    }

    final NodeCache c = new NodeCache(); c.add(doc);
    final TokenList p = new TokenList(1); p.add(token(path));
    ctx.updates.add(new Add(data, input, c, p, ctx.context), ctx);

    return null;
  }