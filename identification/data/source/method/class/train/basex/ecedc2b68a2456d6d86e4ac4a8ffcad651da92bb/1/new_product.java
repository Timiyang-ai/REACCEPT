private Item dropBackup(final QueryContext ctx) throws QueryException {
    final String name = string(checkStr(expr[0], ctx));
    if(!Databases.validName(name)) throw BXDB_NAME.get(info, name);

    final StringList backups = ctx.context.databases.backups(name);
    if(backups.isEmpty()) throw BXDB_WHICHBACK.get(info, name);

    for(final String backup : backups) ctx.updates.add(new BackupDrop(backup, info, ctx), ctx);
    return null;
  }