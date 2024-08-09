private Item restore(final QueryContext qc) throws QueryException {
    // extract database name from backup file
    final String name = string(toToken(exprs[0], qc));
    if(!Databases.validName(name)) throw BXDB_NAME_X.get(info, name);

    // find backup with or without date suffix
    final StringList backups = qc.context.databases.backups(name);
    if(backups.isEmpty()) throw BXDB_NOBACKUP_X.get(info, name);

    final String backup = backups.get(0);
    final String db = Databases.name(backup);
    qc.resources.updates().add(new DBRestore(db, backup, qc, info), qc);
    return null;
  }