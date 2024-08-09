@Override
  public AesCtrJceCipher getPrimitive(ByteString serialized) throws GeneralSecurityException {
    try {
      AesCtrKey keyProto = AesCtrKey.parseFrom(serialized);
      return getPrimitive(keyProto);
    } catch (InvalidProtocolBufferException e) {
      throw new GeneralSecurityException("invalid AesCtr Key");
    }
  }