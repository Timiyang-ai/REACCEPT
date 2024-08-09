private Item add(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final Data data = data(0, ctx);
    final String name = expr.length < 3 ? null : name(checkStr(expr[2], ctx));
    final String path = expr.length < 4 ? null : path(3, ctx);

    // get all items representing document(s):
    final ObjList<Item> docs = new ObjList<Item>(
        (int) Math.max(expr[1].size(), 1));
    final Iter iter = ctx.iter(expr[1]);
    for(Item i; (i = iter.next()) != null;) docs.add(i);

    if(docs.size() > 0) ctx.updates.add(
        new DBAdd(data, input, docs, name, path, ctx.context), ctx);

    return null;
  }