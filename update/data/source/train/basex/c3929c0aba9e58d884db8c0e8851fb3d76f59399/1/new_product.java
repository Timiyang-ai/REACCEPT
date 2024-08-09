private Str serialize(final QueryContext ctx) throws QueryException {
    final ANode node = checkNode(expr[0], ctx);
    final CsvOptions opts = checkOptions(1, Q_OPTIONS, new CsvOptions(), ctx);

    final SerializerOptions sopts = new SerializerOptions();
    sopts.set(SerializerOptions.METHOD, SerialMethod.CSV.toString());
    sopts.set(SerializerOptions.CSV, opts.toString());
    return Str.get(delete(serialize(node.iter(), sopts, INVALIDOPT), '\r'));
  }