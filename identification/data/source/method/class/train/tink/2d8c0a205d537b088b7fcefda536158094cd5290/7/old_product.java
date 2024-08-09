@Override
  public Aead getPrimitive(AesGcmKey keyProto) throws GeneralSecurityException {
    validate(keyProto);
    return new AesGcmJce(keyProto.getKeyValue().toByteArray());
  }