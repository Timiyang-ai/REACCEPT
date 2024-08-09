@Override
  public Aead getPrimitive(MessageLite key) throws GeneralSecurityException {
    if (!(key instanceof AesGcmKey)) {
      throw new GeneralSecurityException("expected AesGcmKey proto");
    }
    AesGcmKey keyProto = (AesGcmKey) key;
    validate(keyProto);
    return new AesGcmJce(keyProto.getKeyValue().toByteArray());
  }