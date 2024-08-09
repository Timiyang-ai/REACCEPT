private Item rename(final QueryContext ctx) throws QueryException {
    final Data data = checkWrite(data(0, ctx), ctx);
    final String source = path(1, ctx);
    final String target = path(2, ctx);

    // the first step of the path should be the database name
    final IntList il = data.resources.docs(source);
    for(int i = 0, is = il.size(); i < is; i++) {
      final int pre = il.get(i);
      final String trg = Rename.target(data, pre, source, target);
      if(trg.isEmpty()) BXDB_EMPTY.thrw(info, this);
      ctx.updates.add(new ReplaceValue(pre, data, info, token(trg)), ctx);
    }
    // rename files
    if(!data.inMemory()) {
      final IOFile src = data.meta.binary(source);
      final IOFile trg = data.meta.binary(target);
      if(src == null || trg == null) UPDBRENAMEERR.thrw(info, src);
      ctx.updates.add(new DBRename(data, src.path(), trg.path(), info), ctx);
    }
    return null;
  }