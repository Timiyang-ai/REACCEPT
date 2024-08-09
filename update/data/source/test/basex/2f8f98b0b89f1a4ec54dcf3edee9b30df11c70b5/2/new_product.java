private Item rename(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final Data data = data(0, ctx);
    final byte[] source = path(checkStr(expr[1], ctx));
    final byte[] target = path(checkStr(expr[2], ctx));

    // the first step of the path should be the database name
    for(final int pre : data.doc(string(source))) {
      final byte[] trg = ACreate.newName(data, pre, source, target);
      if(trg.length == 0) EMPTYPATH.thrw(input, this);
      ctx.updates.add(new ReplaceValue(pre, data, input, trg), ctx);
    }
    return null;
  }