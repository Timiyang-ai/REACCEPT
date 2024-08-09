private static <O extends Options> O parse(final O opts, final Ann ann) throws Exception {
    for(final Item arg : ann.args) opts.parse(string(arg.string(ann.info)));
    return opts;
  }