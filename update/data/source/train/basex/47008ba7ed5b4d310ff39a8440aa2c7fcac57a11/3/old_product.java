private void rename(final Data data, final IOFile src, final IOFile trg, final QueryContext qc)
      throws QueryException {

    if(src.isDir()) {
      // dir -> file? error
      if(trg.exists() && !trg.isDir()) throw BXDB_PATH_X.get(info, src);
      // rename children
      for(final IOFile f : src.children()) rename(data, f, new IOFile(trg, f.name()), qc);
    } else if(src.exists()) {
      // file -> dir? error
      if(trg.isDir()) throw BXDB_PATH_X.get(info, src);
      qc.updates().add(new DBRename(data, src.path(), trg.path(), info), qc);
    }
  }