private Item dropBackup(final QueryContext ctx) throws QueryException {
    final String name = string(checkStr(expr[0], ctx));
    if(!Databases.validName(name)) throw BXDB_NAME.get(info, name);

    final StringList files = DropBackup.backups(name, ctx.context);
    if(files.isEmpty()) throw BXDB_WHICHBACK.get(info, name);

    for(final String file : files) {
      ctx.updates.add(new BackupDrop(file, info, ctx), ctx);
    }
    return null;
  }