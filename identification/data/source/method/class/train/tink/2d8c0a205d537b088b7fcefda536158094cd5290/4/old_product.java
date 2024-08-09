@Override
  public Aead getPrimitive(ByteString serialized) throws GeneralSecurityException {
    try {
      AesEaxKey keyProto = AesEaxKey.parseFrom(serialized);
      return getPrimitive(keyProto);
    } catch (InvalidProtocolBufferException e) {
      throw new GeneralSecurityException("invalid AesEax key");
    }
  }