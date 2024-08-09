@Override
  public AesCtrJceCipher getPrimitive(MessageLite key) throws GeneralSecurityException {
    if (!(key instanceof AesCtrKey)) {
      throw new GeneralSecurityException("expected AesCtrKey proto");
    }
    AesCtrKey keyProto = (AesCtrKey) key;
    validate(keyProto);
    return new AesCtrJceCipher(
        keyProto.getKeyValue().toByteArray(), keyProto.getParams().getIvSize());
  }