private Item dropBackup(final QueryContext qc) throws QueryException {
    final String name = string(checkStr(exprs[0], qc));
    if(!Databases.validName(name)) throw BXDB_NAME.get(info, name);

    final StringList backups = qc.context.databases.backups(name);
    if(backups.isEmpty()) throw BXDB_WHICHBACK.get(info, name);

    final Updates updates = qc.resources.updates();
    for(final String backup : backups) updates.add(new BackupDrop(backup, info, qc), qc);
    return null;
  }