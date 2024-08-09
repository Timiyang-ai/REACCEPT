public static SerializerOptions serializer(final Item it, final SerializerOptions sopts,
      final InputInfo info) throws QueryException {
    new FuncOptions(Q_SPARAM, info).parse(it, sopts, Err.SEROPT);
    return sopts;
  }