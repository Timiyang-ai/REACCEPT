public static void dump(final Item it, final byte[] label, final InputInfo info,
      final QueryContext qc) throws QueryException {
    try {
      final byte[] value;
      if(it == null) {
        value = token(SeqType.EMP.toString());
      } else if(it instanceof FItem || it.type == NodeType.ATT || it.type == NodeType.NSP) {
        value = token(it.toString());
      } else {
        value = it.serialize(SerializerOptions.get(false)).finish();
      }
      dump(value, label, qc);
    } catch(final QueryIOException ex) {
      throw ex.getCause(info);
    }
  }