@Override
  public Aead getPrimitive(ByteString serialized) throws GeneralSecurityException {
    try {
      AesCtrHmacAeadKey keyProto = AesCtrHmacAeadKey.parseFrom(serialized);
      return getPrimitive(keyProto);
    } catch (InvalidProtocolBufferException e) {
      throw new GeneralSecurityException("invalid AesCtrHmacAead key");
    }
  }