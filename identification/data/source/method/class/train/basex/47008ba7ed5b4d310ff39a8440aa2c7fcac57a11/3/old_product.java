private Data create(final QueryInput input, final boolean single, final InputInfo info)
      throws QueryException {

    // check if new databases can be created
    final Context context = qc.context;

    // do not check for existence of input if user has no read permissions
    if(!context.user().has(Perm.READ))
      throw BXXQ_PERM_X.get(info, Util.info(Text.PERM_REQUIRED_X, Perm.READ));

    // check if input points to a single file
    final IO io = input.io;
    if(!io.exists()) throw WHICHRES_X.get(info, io);
    if(single && io.isDir()) throw RESDIR_X.get(info, io);

    // overwrite parsing options with default values
    final boolean mem = !context.options.get(MainOptions.FORCECREATE);
    final MainOptions opts = new MainOptions(context.options, true);
    final Parser parser = new DirParser(io, opts);

    final Data data;
    try {
      data = CreateDB.create(io.dbName(), parser, context, opts, mem);
    } catch(final IOException ex) {
      throw IOERR_X.get(info, ex);
    }
    return addData(data);
  }