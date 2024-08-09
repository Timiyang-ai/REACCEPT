@Test
public void testAdd() {
  for(int i = 0; i < 4000; ++i) {
    final byte[] key = token("keyAdd" + i);
    final int size = i;
    final long pointer = i + 5000L;

    // The add method now returns void, so we don't capture a return value
    cache.add(key, size, pointer);

    // Continue to assert that the cache entry was added correctly
    assertCacheEntry(key, size, pointer);
  }
}