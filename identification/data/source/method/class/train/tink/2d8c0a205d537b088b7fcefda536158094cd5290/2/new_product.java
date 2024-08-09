@Override
  public Aead getPrimitiveFromKey(AesEaxKey keyProto) throws GeneralSecurityException {
    validate(keyProto);
    return new AesEaxJce(keyProto.getKeyValue().toByteArray(), keyProto.getParams().getIvSize());
  }