  @Test
  public void save() throws Exception {
    TestKey key1 = validKeyWithoutExpiration();
    TestKey key2 = validKeyWithExpiration();
    store.add(key1.getPublicKeyRing());
    store.add(key2.getPublicKeyRing());

    assertEquals(RefUpdate.Result.NEW, store.save(newCommitBuilder()));

    assertKeys(key1.getKeyId(), key1);
    assertKeys(key2.getKeyId(), key2);
  }