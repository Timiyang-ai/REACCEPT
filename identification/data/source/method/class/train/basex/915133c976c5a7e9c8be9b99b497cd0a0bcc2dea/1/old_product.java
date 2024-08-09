private DataClip data(final NewInput ni, final String dbname, final MainOptions options)
      throws QueryException {

    // add document node
    final Context ctx = qc.context;
    if(ni.node != null) {
      final MemData mdata = (MemData) ni.node.dbCopy(options).data();
      mdata.update(0, Data.DOC, ni.path);
      return new DataClip(mdata);
    }

    // add input
    final IOFile dbpath = ctx.soptions.dbpath(string(ni.dbname));
    try {
      final Parser parser = new DirParser(ni.io, options, dbpath).target(string(ni.path));
      return new MemBuilder(dbname, parser).dataClip();
    } catch(final IOException ex) {
      throw IOERR_X.get(info, ex);
    }
  }