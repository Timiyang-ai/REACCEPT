@Test
  public void testPromote_shouldPromoteKey() throws Exception {
    int primaryKeyId = 42;
    int newPrimaryKeyId = 43;
    KeysetHandle handle = KeysetHandle.fromKeyset(
        TestUtil.createKeyset(
            createEnabledKey(primaryKeyId),
            createEnabledKey(newPrimaryKeyId)));
    Keyset keyset = KeysetManager
        .withKeysetHandle(handle)
        .promote(newPrimaryKeyId)
        .getKeysetHandle()
        .getKeyset();

    assertThat(keyset.getKeyCount()).isEqualTo(2);
    assertThat(keyset.getPrimaryKeyId()).isEqualTo(newPrimaryKeyId);
  }