private Item backup(final QueryContext ctx) throws QueryException {
    checkCreate(ctx);
    final String name = string(checkStr(expr[0], ctx));
    if(!Databases.validName(name)) throw BXDB_NAME.get(info, name);
    if(!ctx.context.globalopts.dbexists(name)) throw BXDB_WHICH.get(info, name);

    ctx.updates.add(new DBBackup(name, info, ctx), ctx);
    return null;
  }