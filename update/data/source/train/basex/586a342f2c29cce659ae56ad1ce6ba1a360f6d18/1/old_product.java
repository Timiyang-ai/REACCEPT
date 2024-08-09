private Item create(final QueryContext ctx) throws QueryException {
    final String name = string(checkStr(expr[0], ctx));
    if(!MetaData.validName(name, false)) BXDB_NAME.thrw(info, name);
    final Item it = expr.length > 1 ? checkItem(expr[1], ctx) : null;
    final String path = expr.length > 2 ? path(2, ctx) : "";

    ctx.updates.add(new DBCreate(info, name, it, path, ctx), ctx);
    return null;
  }