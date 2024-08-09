private B64 encodeString(final QueryContext ctx) throws QueryException {
    final byte[] str = str(0, ctx);
    final String enc = encoding(1, BIN_UE_X, ctx);
    if(str == null) return null;
    try {
      return new B64(FNConvert.toBinary(str, enc));
    } catch(final CharacterCodingException ex) {
      throw BIN_EE.get(info);
    }
  }