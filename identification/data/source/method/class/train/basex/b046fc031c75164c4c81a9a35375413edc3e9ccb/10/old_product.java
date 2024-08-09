private Item replace(final QueryContext qc) throws QueryException {
    final Data data = checkData(qc);
    final String path = path(1, qc);
    final Item item = checkItem(exprs[2], qc);
    final Options opts = checkOptions(3, Q_OPTIONS, new Options(), qc);

    // remove old documents
    final Resources res = data.resources;
    final IntList pre = res.docs(path, true);
    final Updates updates = qc.resources.updates();
    for(int p = 0; p < pre.size(); p++) {
      updates.add(new DeleteNode(pre.get(p), data, info), qc);
    }

    // delete binary resources
    final IOFile bin = data.inMemory() ? null : data.meta.binary(path);
    if(bin != null) {
      if(bin.exists() || item instanceof Bin) {
        if(bin.isDir()) throw BXDB_DIR.get(info, path);
        updates.add(new DBStore(data, path, item, info), qc);
      } else {
        updates.add(new DBAdd(data, checkInput(item, token(path)), opts, qc, info), qc);
      }
    }
    return null;
  }