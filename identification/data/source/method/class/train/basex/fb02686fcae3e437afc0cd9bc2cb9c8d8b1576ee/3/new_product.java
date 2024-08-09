public static void trace(final Item it, final byte[] label, final InputInfo info,
      final QueryContext qc) throws QueryException {
    try {
      trace(it == null ? token(SeqType.EMP.toString()) :
        it.serialize(SerializerMode.DEBUG.get()).finish(), label, qc);
    } catch(final QueryIOException ex) {
      throw ex.getCause(info);
    }
  }