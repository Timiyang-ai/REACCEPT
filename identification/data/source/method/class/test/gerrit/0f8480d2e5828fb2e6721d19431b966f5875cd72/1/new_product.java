public PGPPublicKeyRingCollection get(long keyId)
      throws PGPException, IOException {
    return new PGPPublicKeyRingCollection(get(keyId, null));
  }