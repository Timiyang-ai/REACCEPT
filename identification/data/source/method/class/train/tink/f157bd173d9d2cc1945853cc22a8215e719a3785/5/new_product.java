@Override
  public Aead getPrimitiveFromKey(AesCtrHmacAeadKey keyProto) throws GeneralSecurityException {
    validate(keyProto);
    return new EncryptThenAuthenticate(
        (IndCpaCipher) Registry.getPrimitive(AesCtrKeyManager.TYPE_URL, keyProto.getAesCtrKey()),
        (Mac) Registry.getPrimitive(MacConfig.HMAC_TYPE_URL, keyProto.getHmacKey()),
        keyProto.getHmacKey().getParams().getTagSize());
  }