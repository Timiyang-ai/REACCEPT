private Item store(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final Data data = data(0, ctx);
    final String key = path(1, ctx);
    if(!new IOFile(key).valid()) RESINV.thrw(input, key);

    final byte[] val = checkBin(expr[2], ctx);
    ctx.updates.add(new DBPut(data, token(key), val, input), ctx);
    return null;
  }