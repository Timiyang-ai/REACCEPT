public KeysetHandle getPublicKeysetHandle() throws GeneralSecurityException {
    Keyset.Builder keysetBuilder = Keyset.newBuilder();
    for (Keyset.Key key : keyset.getKeyList()) {
      KeyData keyData = createPublicKeyData(key.getKeyData());
      keysetBuilder.addKey(Keyset.Key.newBuilder()
          .mergeFrom(key)
          .setKeyData(keyData)
          .build());
    }
    keysetBuilder.setPrimaryKeyId(keyset.getPrimaryKeyId());
    return new KeysetHandle(keysetBuilder.build());
  }