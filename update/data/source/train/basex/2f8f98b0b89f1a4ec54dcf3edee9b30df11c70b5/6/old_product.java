private Item delete(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final String path = path(string(checkStr(expr[0], ctx)));

    // the first step of the path should be the database name
    final int pos = path.indexOf('/');
    if(pos <= 0) NODB.thrw(input, path);
    final byte[] db = token(path.substring(0, pos));
    final Data data = ctx.resource.data(db, input);

    final String trg = path.substring(pos + 1);

    final int[] docs = data.doc(trg);
    for(final int pre : docs)
      ctx.updates.add(new DeleteNode(pre, data, input), ctx);

    return null;
  }