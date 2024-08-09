public SecretKey extract(SecretKey salt, byte[] ikm) {
    requireNonNull(ikm, "ikm must not be null");
    if (salt == null) {
      salt = new SecretKeySpec(new byte[hash.getByteLength()], hash.getAlgorithm());
    }

    Mac mac = initMac(salt);
    byte[] keyBytes = mac.doFinal(ikm);
    return new SecretKeySpec(keyBytes, hash.getAlgorithm());
  }