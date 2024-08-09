@Test
  public void testToString() throws Exception {
    String keyValue = "01234567890123456";
    Keyset keyset =  TestUtil.createKeyset(TestUtil.createKey(
        TestUtil.createHmacKey(keyValue),
        42,
        KeyStatusType.ENABLED,
        OutputPrefixType.TINK));
    KeysetHandle handle = CleartextKeysetHandleFactory.fromBinaryFormat(keyset.toByteArray());
    assertEquals(keyset, handle.getKeyset());

    String keysetInfo = handle.toString();
    assertFalse(keysetInfo.contains(keyValue));
    assertTrue(TextFormat.printToUnicodeString(handle.getKeyset()).contains(keyValue));
  }