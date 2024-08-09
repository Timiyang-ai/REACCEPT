private Item rename(final QueryContext ctx) throws QueryException {
    final Data data = ctx.resource.data(checkStr(expr[0], ctx), input);
    final byte[] path = checkStr(expr[1], ctx);
    final byte[] newpath = checkStr(expr[2], ctx);

    final int[] docs = data.doc(string(path));
    for(final int pre : docs) {
      final byte[] nm = newName(data, pre, path, newpath);
      ctx.updates.add(new ReplaceValue(pre, data, input, nm), ctx);
    }

    return null;
  }