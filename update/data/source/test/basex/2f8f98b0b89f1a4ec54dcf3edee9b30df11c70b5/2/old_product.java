private Item rename(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final String path = path(string(checkStr(expr[0], ctx)));

    // the first step of the path should be the database name
    final int pos = path.indexOf('/');
    if(pos <= 0) NODB.thrw(input, path);
    final byte[] db = token(path.substring(0, pos));
    final Data data = ctx.resource.data(db, input);

    final byte[] src = token(path.substring(pos + 1));
    final byte[] trg = token(path(string(checkStr(expr[1], ctx))));

    final int[] docs = data.doc(string(src));
    for(final int pre : docs) {
      final byte[] nm = newName(data, pre, src, trg);
      ctx.updates.add(new ReplaceValue(pre, data, input, nm), ctx);
    }

    return null;
  }