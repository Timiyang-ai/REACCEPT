private Str serialize(final QueryContext ctx) throws QueryException {
    final ANode node = checkNode(expr[0], ctx);
    final Item opt = expr.length > 1 ? expr[1].item(ctx, info) : null;
    final TokenMap map = new FuncParams(Q_OPTIONS, info).parse(opt);

    final ArrayOutput ao = new ArrayOutput();
    final SerializerProp props = new SerializerProp();
    if(map.contains(HEADER)) props.set(S_CSV_HEADER, string(map.get(HEADER)));
    if(map.contains(SEPARATOR)) props.set(S_CSV_SEPARATOR, string(map.get(SEPARATOR)));

    try {
      // run serialization
      final Serializer ser = new CsvSerializer(ao, props);
      ser.serialize(node);
      ser.close();
    } catch(final SerializerException ex) {
      throw ex.getCause(info);
    } catch(final IOException ex) {
      SERANY.thrw(info, ex);
    }
    return Str.get(delete(ao.toArray(), '\r'));
  }