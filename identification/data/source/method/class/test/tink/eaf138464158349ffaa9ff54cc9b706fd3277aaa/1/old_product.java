@GuardedBy("this")
  public synchronized KeysetManager promote(int keyId) throws GeneralSecurityException {
    for (int i = 0; i < keysetBuilder.getKeyCount(); i++) {
      Keyset.Key key = keysetBuilder.getKey(i);
      if (key.getKeyId() == keyId) {
        if (!key.getStatus().equals(KeyStatusType.ENABLED)) {
          throw new GeneralSecurityException(
              "cannot promote key because it's not enabled: " + keyId);
        }
        keysetBuilder.setPrimaryKeyId(keyId);
        return this;
      }
    }
    throw new GeneralSecurityException("key not found: " + keyId);
  }