private Item drop(final QueryContext qc) throws QueryException {
    final String name = string(checkStr(exprs[0], qc));
    if(!Databases.validName(name)) throw BXDB_NAME.get(info, name);
    if(!qc.context.globalopts.dbexists(name)) throw BXDB_WHICH.get(info, name);
    qc.resources.updates().add(new DBDrop(name, info, qc), qc);
    return null;
  }