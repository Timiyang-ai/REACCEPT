@Override
  public AesCtrJceCipher getPrimitive(ByteString serializedKey) throws GeneralSecurityException {
    try {
      AesCtrKey keyProto = AesCtrKey.parseFrom(serializedKey);
      return getPrimitive(keyProto);
    } catch (InvalidProtocolBufferException e) {
      throw new GeneralSecurityException("expected serialized AesCtrKey proto", e);
    }
  }