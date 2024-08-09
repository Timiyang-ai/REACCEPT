private Item rename(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final Data data = data(0, ctx);
    final String src = path(1, ctx);
    final String trg = path(2, ctx);

    // the first step of the path should be the database name
    final IntList il = data.docs(src);
    for(int i = 0, is = il.size(); i < is; i++) {
      final int pre = il.get(i);
      final String target = Rename.target(data, pre, src, trg);
      if(target.isEmpty()) EMPTYPATH.thrw(input, this);
      ctx.updates.add(new ReplaceValue(pre, data, input, token(target)), ctx);
    }
    // rename files
    if(data.meta.binary(src) != null && data.meta.binary(trg) != null)
      ctx.updates.add(new DBRename(data, src, trg, input), ctx);

    return null;
  }