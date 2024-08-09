@Override
  public Aead getPrimitive(AesCtrHmacAeadKey keyProto) throws GeneralSecurityException {
    validate(keyProto);
    return new EncryptThenAuthenticate(
        Registry.INSTANCE.getPrimitive(AesCtrKeyManager.TYPE_URL, keyProto.getAesCtrKey()),
        Registry.INSTANCE.getPrimitive(HmacKeyManager.TYPE_URL, keyProto.getHmacKey()),
        keyProto.getHmacKey().getParams().getTagSize());
  }