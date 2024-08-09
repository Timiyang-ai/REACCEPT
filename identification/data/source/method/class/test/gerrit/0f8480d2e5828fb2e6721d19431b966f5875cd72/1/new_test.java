  @Test
  public void get() throws Exception {
    TestKey key1 = validKeyWithoutExpiration();
    tr.branch(REFS_GPG_KEYS)
        .commit()
        .add(keyObjectId(key1.getKeyId()).name(), key1.getPublicKeyArmored())
        .create();
    TestKey key2 = validKeyWithExpiration();
    tr.branch(REFS_GPG_KEYS)
        .commit()
        .add(keyObjectId(key2.getKeyId()).name(), key2.getPublicKeyArmored())
        .create();

    assertKeys(key1.getKeyId(), key1);
    assertKeys(key2.getKeyId(), key2);
  }