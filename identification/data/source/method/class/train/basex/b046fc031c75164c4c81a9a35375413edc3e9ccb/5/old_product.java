private B64Stream retrieve(final QueryContext qc) throws QueryException {
    final Data data = checkData(qc);
    final String path = path(1, qc);
    if(data.inMemory()) throw BXDB_MEM.get(info, data.meta.name);

    final IOFile file = data.meta.binary(path);
    if(file == null || !file.exists() || file.isDir()) throw WHICHRES.get(info, path);
    return new B64Stream(file, IOERR);
  }