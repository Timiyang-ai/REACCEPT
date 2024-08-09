public static String add(final String name, final String target,
      final InputSource input, final Context ctx, final Add cmd,
      final boolean lock) throws BaseXException {

    final Data data = ctx.data;
    if(data == null) return PROCNODB;

    String trg = path(target);
    if(!trg.isEmpty()) trg = trg + '/';

    final SAXSource sax = new SAXSource(input);
    final Parser parser = new SAXWrapper(sax, name, trg, ctx.prop);
    try {
      if(lock) ctx.register(true);
      return add(parser, ctx, trg, name, cmd);
    } finally {
      if(lock) ctx.unregister(true);
    }
  }