@Test
    public void testIncludeTailCache() {
        final long segmentId = 0L;
        final long baseOffset = 1000;
        @Cleanup
        val cacheFactory = new InMemoryCacheFactory();
        @Cleanup
        val keyCache = new ContainerKeyCache(CONTAINER_ID, cacheFactory);
        val expectedResult = new HashMap<TestKey, CacheBucketOffset>();

        // Insert some pre-existing values.
        for (int i = 0; i < KEYS_PER_SEGMENT; i++) {
            long offset = baseOffset + i;
            val keyHash = newSimpleHash();
            keyCache.updateSegmentIndexOffset(segmentId, offset);
            long updateResult = keyCache.includeExistingKey(segmentId, keyHash, offset);
            Assert.assertEquals("Unexpected result from includeExistingKey() for new insertion.", offset, updateResult);
            expectedResult.put(new TestKey(segmentId, keyHash), new CacheBucketOffset(offset, false));
        }
        // Perform updates.
        val rnd = new Random(0);
        boolean successfulUpdate = false;
        val updateBatch = new HashMap<UUID, CacheBucketOffset>();
        for (val e : expectedResult.entrySet()) {
            // Every other update will try to set an obsolete offset. We need to verify that such a case will not be accepted.
            successfulUpdate = !successfulUpdate;
            val existingOffset = e.getValue().getSegmentOffset();
            val segmentIndexOffset = keyCache.getSegmentIndexOffset(e.getKey().segmentId);

            long newOffset;
            boolean isRemoved;
            if (successfulUpdate) {
                newOffset = existingOffset + 1;
                isRemoved = existingOffset % 3 == 0; // Need to pick odd number since only odd offsets are successful.
                e.setValue(new CacheBucketOffset(newOffset, isRemoved));
            } else {
                newOffset = existingOffset - 1;
                isRemoved = existingOffset % 4 == 0; // Need to pick even number since only even offsets are unsuccessful.
            }

            updateBatch.put(e.getKey().keyHash, new CacheBucketOffset(newOffset, isRemoved));
        }

        // Update the cache, then check it.
        keyCache.includeTailCache(segmentId, updateBatch);
        checkCache(expectedResult, keyCache);
    }