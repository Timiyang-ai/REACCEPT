final Item copy(final QueryContext qc, final boolean keep) throws QueryException {
    final String name = string(toToken(exprs[0], qc));
    final String newname = string(toToken(exprs[1], qc));

    if(!Databases.validName(name)) throw BXDB_NAME_X.get(info, name);
    if(!Databases.validName(newname)) throw BXDB_NAME_X.get(info, newname);

    // source database does not exist
    if(!qc.context.soptions.dbExists(name)) throw BXDB_WHICH_X.get(info, name);
    if(name.equals(newname)) throw BXDB_SAME_X.get(info, name, newname);

    qc.updates().add(keep ? new DBCopy(name, newname, info, qc) :
      new DBAlter(name, newname, info, qc), qc);
    return null;
  }