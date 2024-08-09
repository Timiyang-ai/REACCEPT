@Override
  public Mac getPrimitive(ByteString serializedKey) throws GeneralSecurityException {
    try {
      HmacKey keyProto = HmacKey.parseFrom(serializedKey);
      return getPrimitive(keyProto);
    } catch (InvalidProtocolBufferException e) {
      throw new GeneralSecurityException("expected serialized HmacKey proto", e);
    }
  }