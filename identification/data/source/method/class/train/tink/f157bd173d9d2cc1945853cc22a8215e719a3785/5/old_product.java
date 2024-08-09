@Override
  public Aead getPrimitive(MessageLite key) throws GeneralSecurityException {
    if (!(key instanceof AesCtrHmacAeadKey)) {
      throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
    }
    AesCtrHmacAeadKey keyProto = (AesCtrHmacAeadKey) key;
    validate(keyProto);
    return new EncryptThenAuthenticate(
        (IndCpaCipher) Registry.getPrimitive(
            AesCtrKeyManager.TYPE_URL, keyProto.getAesCtrKey()),
        (Mac) Registry.getPrimitive(MacConfig.HMAC_TYPE_URL, keyProto.getHmacKey()),
        keyProto.getHmacKey().getParams().getTagSize());
  }