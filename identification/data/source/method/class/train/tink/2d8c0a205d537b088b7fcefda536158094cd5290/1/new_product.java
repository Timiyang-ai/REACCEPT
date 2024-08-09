@Override
  public IndCpaCipher getPrimitiveFromKey(AesCtrKey keyProto) throws GeneralSecurityException {
    validate(keyProto);
    return new AesCtrJceCipher(
        keyProto.getKeyValue().toByteArray(), keyProto.getParams().getIvSize());
  }