private Item copy(final QueryContext qc, final boolean keep) throws QueryException {
    final String name = string(checkStr(exprs[0], qc));
    final String newname = string(checkStr(exprs[1], qc));

    if(!Databases.validName(name)) throw BXDB_NAME.get(info, name);
    if(!Databases.validName(newname)) throw BXDB_NAME.get(info, newname);

    // source database does not exist
    final GlobalOptions goptions = qc.context.globalopts;
    if(!goptions.dbexists(name)) throw BXDB_WHICH.get(info, name);
    if(name.equals(newname)) throw BXDB_SAME.get(info, name, newname);

    qc.resources.updates().add(keep ? new DBCopy(name, newname, info, qc) :
      new DBAlter(name, newname, info, qc), qc);
    return null;
  }