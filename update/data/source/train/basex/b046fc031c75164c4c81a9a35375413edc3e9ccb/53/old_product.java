private Item restore(final QueryContext qc) throws QueryException {
    // extract database name from backup file
    final String name = string(checkStr(exprs[0], qc));
    if(!Databases.validName(name)) throw BXDB_NAME.get(info, name);

    // find backup with or without date suffix
    final StringList backups = qc.context.databases.backups(name);
    if(backups.isEmpty()) throw BXDB_NOBACKUP.get(info, name);

    final String backup = backups.get(0);
    final String db = Databases.name(backup);
    qc.resources.updates().add(new DBRestore(db, backup, qc, info), qc);
    return null;
  }