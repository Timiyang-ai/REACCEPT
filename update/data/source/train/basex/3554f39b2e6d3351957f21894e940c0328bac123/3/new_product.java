private ANode xmlEntry(final QueryContext ctx, final boolean html)
      throws QueryException {

    final Prop prop = ctx.context.prop;
    final IO io = new IOContent(entry(ctx));
    try {
      final Parser p = html ? new HTMLParser(io, "", prop) :
        Parser.xmlParser(io, prop, "");
      return new DBNode(MemBuilder.build(p, prop, ""), 0);
    } catch(final IOException ex) {
      throw SAXERR.thrw(input, ex);
    }
  }