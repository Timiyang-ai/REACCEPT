@Override
  public AesCtrJceCipher getPrimitive(AesCtrKey keyProto) throws GeneralSecurityException {
    validate(keyProto);
    return new AesCtrJceCipher(keyProto.getKeyValue().toByteArray(),
        keyProto.getParams().getIvSize());
  }