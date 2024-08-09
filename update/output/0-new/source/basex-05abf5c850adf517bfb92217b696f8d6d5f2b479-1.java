@Test
  public void testAdd() {
    for(int i = 0; i < 4000; ++i) {
      final byte[] key = token("keyAdd" + i);
      final long pointer = i + 5000L;

      cache.add(key, i, pointer);
      assertCacheEntry(key, i, pointer);
    }
  }