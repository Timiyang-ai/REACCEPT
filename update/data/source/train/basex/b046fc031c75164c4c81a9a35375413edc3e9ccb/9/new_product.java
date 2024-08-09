private B64 encodeString(final QueryContext qc) throws QueryException {
    final byte[] str = str(0, qc);
    final String enc = toEncoding(1, BIN_UE_X, qc);
    if(str == null) return null;
    try {
      return new B64(enc == null || enc == UTF8 ? str : FNConvert.toBinary(str, enc));
    } catch(final CharacterCodingException ex) {
      throw BIN_CE_X.get(info, ex);
    }
  }