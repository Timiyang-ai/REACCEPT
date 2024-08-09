private Str serialize(final QueryContext ctx) throws QueryException {
    final ANode node = checkNode(expr[0], ctx);
    final Item opt = expr.length > 1 ? expr[1].item(ctx, info) : null;
    final CsvOptions opts = new CsvOptions();
    new FuncOptions(Q_OPTIONS, info).parse(opt, opts);

    final SerializerOptions sopts = new SerializerOptions();
    sopts.set(S_METHOD, M_CSV);
    sopts.set(S_CSV, opts.toString());
    // serialize node and remove carriage returns
    return Str.get(delete(serialize(node.iter(), sopts), '\r'));
  }