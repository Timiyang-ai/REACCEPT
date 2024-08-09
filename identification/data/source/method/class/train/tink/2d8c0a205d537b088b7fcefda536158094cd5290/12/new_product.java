@Override
  protected Aead getPrimitiveFromKey(AesGcmKey key) throws GeneralSecurityException {
    validate(key);
    return new AesGcmJce(key.getKeyValue().toByteArray());
  }