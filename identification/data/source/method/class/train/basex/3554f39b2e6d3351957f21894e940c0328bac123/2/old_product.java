private ANode xmlEntry(final QueryContext ctx) throws QueryException {
    final Prop prop = ctx.context.prop;
    final IO io = new IOContent(entry(ctx));
    try {
      final Parser p = Parser.fileParser(io, prop, "");
      return new DBNode(MemBuilder.build(p, prop, ""), 0);
    } catch(final IOException ex) {
      throw SAXERR.thrw(input, ex);
    }
  }