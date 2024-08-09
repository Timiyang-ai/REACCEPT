@Override
  public Aead getPrimitive(MessageLite key) throws GeneralSecurityException {
    if (!(key instanceof AesCtrHmacAeadKey)) {
      throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
    }
    AesCtrHmacAeadKey keyProto = (AesCtrHmacAeadKey) key;
    validate(keyProto);
    return new EncryptThenAuthenticate(
        Registry.INSTANCE.getPrimitive(AesCtrKeyManager.TYPE_URL, keyProto.getAesCtrKey()),
        Registry.INSTANCE.getPrimitive(HmacKeyManager.TYPE_URL, keyProto.getHmacKey()),
        keyProto.getHmacKey().getParams().getTagSize());
  }