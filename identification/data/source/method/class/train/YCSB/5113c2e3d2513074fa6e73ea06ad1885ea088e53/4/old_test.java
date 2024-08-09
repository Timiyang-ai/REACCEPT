@Test
  public void testScan() {
    final DB client = getDB();

    final String table = getClass().getSimpleName();

    // Insert a bunch of documents.
    for (int i = 0; i < 100; ++i) {
      HashMap<String, ByteIterator> inserted =
          new HashMap<String, ByteIterator>();
      inserted.put("a", new ByteArrayByteIterator(new byte[] {
          (byte) (i & 0xFF), (byte) (i >> 8 & 0xFF), (byte) (i >> 16 & 0xFF),
          (byte) (i >> 24 & 0xFF) }));
      int result = client.insert(table, padded(i), inserted);
      assertThat("Insert did not return success (0).", result, is(0));
    }

    Set<String> keys = Collections.singleton("a");
    Vector<HashMap<String, ByteIterator>> results =
        new Vector<HashMap<String, ByteIterator>>();
    int result = client.scan(table, "00050", 5, null, results);
    assertThat("Read did not return success (0).", result, is(0));
    assertThat(results.size(), is(5));
    for (int i = 0; i < 5; ++i) {
      HashMap<String, ByteIterator> read = results.get(i);
      for (String key : keys) {
        ByteIterator iter = read.get(key);

        assertThat("Did not read the inserted field: " + key, iter,
            notNullValue());
        assertTrue(iter.hasNext());
        assertThat(iter.nextByte(), is(Byte.valueOf((byte) ((i + 50) & 0xFF))));
        assertTrue(iter.hasNext());
        assertThat(iter.nextByte(),
            is(Byte.valueOf((byte) ((i + 50) >> 8 & 0xFF))));
        assertTrue(iter.hasNext());
        assertThat(iter.nextByte(),
            is(Byte.valueOf((byte) ((i + 50) >> 16 & 0xFF))));
        assertTrue(iter.hasNext());
        assertThat(iter.nextByte(),
            is(Byte.valueOf((byte) ((i + 50) >> 24 & 0xFF))));
        assertFalse(iter.hasNext());
      }
    }
  }