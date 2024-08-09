private Item add(final QueryContext ctx) throws QueryException {
    final byte[] name = expr.length < 3 ? null :
      token(path(string(checkStr(expr[2], ctx))));
    final byte[] path = expr.length < 4 ? null :
      token(path(string(checkStr(expr[3], ctx))));

    // get all items representing document(s):
    final ArrayList<Item> docs = new ArrayList<Item>();
    final Iter iter = ctx.iter(expr[1]);
    for(Item i; (i = iter.next()) != null;) docs.add(i);

    if(docs.size() > 0) {
      final Data data = ctx.resource.data(checkStr(expr[0], ctx), input);
      ctx.updates.add(new Add(data, input, docs, name, path, ctx.context), ctx);
    }
    return null;
  }