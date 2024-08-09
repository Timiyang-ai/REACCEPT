@GuardedBy("this")
  @Deprecated
  public synchronized KeysetManager promote(int keyId) throws GeneralSecurityException {
    return setPrimary(keyId);
  }