private Item rename(final QueryContext qc) throws QueryException {
    final Data data = checkData(qc);
    final String source = path(1, qc);
    final String target = path(2, qc);

    // the first step of the path should be the database name
    final Updates updates = qc.resources.updates();
    final IntList il = data.resources.docs(source);
    final int is = il.size();
    for(int i = 0; i < is; i++) {
      final int pre = il.get(i);
      final String trg = Rename.target(data, pre, source, target);
      if(trg.isEmpty() || trg.endsWith("/") || trg.endsWith("."))
        throw BXDB_RENAME_X.get(info, trg);
      updates.add(new ReplaceValue(pre, data, info, token(trg)), qc);
    }
    // rename files
    if(!data.inMemory()) {
      final IOFile src = data.meta.binary(source);
      final IOFile trg = data.meta.binary(target);
      if(src == null || trg == null) throw UPDBRENAME_X.get(info, src);
      updates.add(new DBRename(data, src.path(), trg.path(), info), qc);
    }
    return null;
  }