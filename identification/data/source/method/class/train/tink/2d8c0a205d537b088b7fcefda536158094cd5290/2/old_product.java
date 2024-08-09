@Override
  public Aead getPrimitive(MessageLite key) throws GeneralSecurityException {
    if (!(key instanceof AesEaxKey)) {
      throw new GeneralSecurityException("expected AesEaxKey proto");
    }
    AesEaxKey keyProto = (AesEaxKey) key;
    validate(keyProto);
    return new AesEaxJce(keyProto.getKeyValue().toByteArray(), keyProto.getParams().getIvSize());
  }