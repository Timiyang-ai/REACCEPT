@Test
public void testAdd() {
    for(int i = 0; i < 4000; ++i) {
        final byte[] key = token("keyAdd" + i);
        final int size = i;
        final long pointer = i + 5000L;

        // Call the add method without expecting a return value
        cache.add(key, size, pointer);
        
        // Verify the state of the cache after the add operation
        assertCacheEntry(key, size, pointer);
    }
}