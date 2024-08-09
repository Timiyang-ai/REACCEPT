private Str serialize(final QueryContext ctx) throws QueryException {
    final ANode node = checkNode(expr[0], ctx);
    final Item opt = expr.length > 1 ? expr[1].item(ctx, info) : null;
    final TokenMap map = new FuncParams(Q_OPTIONS, info).parse(opt);

    final SerializerOptions opts = new SerializerOptions();
    opts.set(S_METHOD, M_CSV);
    opts.set(S_CSV, options(map).toString());
    // serialize node and remove carriage returns
    return Str.get(delete(serialize(node.iter(), opts), '\r'));
  }