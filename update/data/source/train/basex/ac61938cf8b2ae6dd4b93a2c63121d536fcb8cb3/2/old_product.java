private Item delete(final QueryContext ctx) throws QueryException {
    final Data data = checkWrite(data(0, ctx), ctx);
    final String path = path(1, ctx);

    // delete XML resources
    final IntList docs = data.resources.docs(path);
    for(int i = 0, is = docs.size(); i < is; i++) {
      ctx.updates.add(new DeleteNode(docs.get(i), data, info), ctx);
    }
    // delete raw resources
    if(!data.inMemory()) {
      final IOFile bin = data.meta.binary(path);
      if(bin == null) UPDBDELERR.thrw(info, path);
      ctx.updates.add(new DBDelete(data, path, info), ctx);
    }
    return null;
  }