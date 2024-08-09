@Override
  public Aead getPrimitive(ByteString serialized) throws GeneralSecurityException {
    try {
      AesGcmKey keyProto = AesGcmKey.parseFrom(serialized);
      return new AesGcmJce(keyProto.getKeyValue().toByteArray());
    } catch (InvalidProtocolBufferException e) {
      throw new GeneralSecurityException("invalid AesGcm key");
    }
  }