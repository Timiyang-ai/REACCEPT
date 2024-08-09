@Override
  public Mac getPrimitive(ByteString serialized) throws GeneralSecurityException {
    try {
      HmacKey keyProto = HmacKey.parseFrom(serialized);
      return getPrimitive(keyProto);
    } catch (InvalidProtocolBufferException e) {
      throw new GeneralSecurityException("invalid Hmac key");
    }
  }