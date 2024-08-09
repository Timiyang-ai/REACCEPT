@Override
  public Aead getPrimitive(ByteString serializedKey) throws GeneralSecurityException {
    try {
      AesGcmKey keyProto = AesGcmKey.parseFrom(serializedKey);
      return new AesGcmJce(keyProto.getKeyValue().toByteArray());
    } catch (InvalidProtocolBufferException e) {
      throw new GeneralSecurityException("expected AesGcmKey proto");
    }
  }