@Test
public void testAdd() {
  for(int i = 0; i < 4000; ++i) {
    final byte[] key = token("keyAdd" + i);
    final int size = i;
    final long pointer = i + 5000L;

    // Since add() method now returns void, we cannot check the return value.
    // Instead, we directly verify if the entry has been added to the cache.
    cache.add(key, size, pointer);
    assertCacheEntry(key, size, pointer);

    // Optionally, if there's a way to check the size of the cache or the existence of the entry,
    // we can add additional assertions here to ensure the entry was indeed added.
    // Example (assuming a method exists to check if an entry exists):
    // assertTrue("Entry should exist in cache.", cache.containsEntry(key));
  }
}