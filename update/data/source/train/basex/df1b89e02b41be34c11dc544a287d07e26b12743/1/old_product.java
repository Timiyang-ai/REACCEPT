private Item store(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final Data data = data(0, ctx);
    final String path = path(1, ctx);
    final IOFile file = data.meta.binary(path);
    if(file == null || file.isDir()) RESINV.thrw(input, path);

    final byte[] val = checkBin(expr[2], ctx);
    ctx.updates.add(new DBStore(data, token(path), val, input), ctx);
    return null;
  }