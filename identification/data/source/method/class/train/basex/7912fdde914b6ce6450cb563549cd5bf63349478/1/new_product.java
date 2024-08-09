private Str serialize(final QueryContext ctx) throws QueryException {
    final ANode node = checkNode(expr[0], ctx);
    final Item opt = expr.length > 1 ? expr[1].item(ctx, info) : null;
    final TokenMap map = new FuncParams(Q_OPTIONS, info).parse(opt);

    final SerializerProp props = new SerializerProp();
    props.set(S_METHOD, M_CSV);
    // create csv properties and set options
    final CsvProp cprop = new CsvProp();
    final byte[] header = map.get(HEADER);
    if(header != null) cprop.set(CsvProp.HEADER, Util.yes(string(header)));
    final byte[] sep = map.get(SEPARATOR);
    if(sep != null) cprop.set(CsvProp.SEPARATOR, string(sep));
    props.set(S_CSV, cprop.toString());

    // serialize node
    return Str.get(delete(serialize(node.iter(), props), '\r'));
  }