@Override
  public Mac getPrimitiveFromKey(HmacKey keyProto) throws GeneralSecurityException {
    validate(keyProto);
    HashType hash = keyProto.getParams().getHash();
    byte[] keyValue = keyProto.getKeyValue().toByteArray();
    SecretKeySpec keySpec = new SecretKeySpec(keyValue, "HMAC");
    int tagSize = keyProto.getParams().getTagSize();
    switch (hash) {
      case SHA1:
        return new MacJce("HMACSHA1", keySpec, tagSize);
      case SHA256:
        return new MacJce("HMACSHA256", keySpec, tagSize);
      case SHA512:
        return new MacJce("HMACSHA512", keySpec, tagSize);
      default:
        throw new GeneralSecurityException("unknown hash");
    }
  }