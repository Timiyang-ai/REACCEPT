private Item drop(final QueryContext ctx) throws QueryException {
    ctx.updates.add(new DBDrop(checkData(ctx), info, ctx), ctx);
    return null;
  }