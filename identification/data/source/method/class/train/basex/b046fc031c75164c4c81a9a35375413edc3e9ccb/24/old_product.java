private Data create(final QueryInput input, final boolean single, final IO baseIO,
      final InputInfo info) throws QueryException {

    // check if new databases can be created
    final Context context = qc.context;

    // do not check input if no read permissions are given
    if(!qc.context.user.has(Perm.READ))
      throw BXXQ_PERM.get(info, Util.info(Text.PERM_REQUIRED_X, Perm.READ));

    // check if input is an existing file
    final IO source = checkPath(input, baseIO, info);
    if(single && source.isDir()) WHICHRES.get(info, baseIO);

    // overwrite parsing options with default values
    final MainOptions opts = context.options;
    final Option<?>[] options = { MainOptions.SKIPCORRUPT, MainOptions.ADDARCHIVES,
        MainOptions.ADDRAW, MainOptions.PARSER, MainOptions.CREATEFILTER };
    final Object[] values = new Object[options.length];
    final int ol = options.length;
    for(int o = 0; o < ol; o++) {
      final Option<?> option = options[o];
      values[o] = opts.get(option);
      opts.put(option, option.value());
    }
    try {
      final boolean fc = context.options.get(MainOptions.FORCECREATE);
      return addData(CreateDB.create(source.dbname(), new DirParser(source, opts), context, !fc));
    } catch(final IOException ex) {
      throw IOERR.get(info, ex);
    } finally {
      // reset original values
      for(int o = 0; o < ol; o++) opts.put(options[o], values[o]);
      input.path = "";
    }
  }