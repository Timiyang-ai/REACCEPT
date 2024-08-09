private Item restore(final QueryContext ctx) throws QueryException {
    // extract database name from backup file
    final String name = string(checkStr(expr[0], ctx));
    final String db = Restore.dbName(name);
    if(!Databases.validName(db)) throw BXDB_NAME.get(info, db);

    final IOFile backup = Restore.backupFile(name, ctx.context);
    if(backup == null) throw BXDB_NOBACKUP.get(info, name);

    ctx.updates.add(new DBRestore(db, backup, ctx, info), ctx);
    return null;
  }