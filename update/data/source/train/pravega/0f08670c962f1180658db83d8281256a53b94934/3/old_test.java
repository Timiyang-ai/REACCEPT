@Test
    public void testIncludeExistingKey() {
        @Cleanup
        val cacheFactory = new InMemoryCacheFactory();
        @Cleanup
        val keyCache = new ContainerKeyCache(CONTAINER_ID, cacheFactory);
        val expectedResult = new HashMap<TestKey, CacheBucketOffset>();

        // Insert.
        for (long offset = 0; offset < KEYS_PER_SEGMENT; offset++) {
            // We reuse the same key hash across multiple "segments", to make sure that segmentId does indeed partition
            // the cache.
            val keyHash = newSimpleHash();
            for (long segmentId = 0; segmentId < SEGMENT_COUNT; segmentId++) {
                keyCache.updateSegmentIndexOffset(segmentId, offset);
                long updateResult = keyCache.includeExistingKey(segmentId, keyHash, offset);
                Assert.assertEquals("Unexpected result from includeExistingKey() for new insertion.", offset, updateResult);
                expectedResult.put(new TestKey(segmentId, keyHash), new CacheBucketOffset(offset, false));
            }
        }

        // Verify the cache, after inserts.
        checkCache(expectedResult, keyCache);

        // Perform updates.
        val rnd = new Random(0);
        boolean successfulUpdate = false;
        for (val e : expectedResult.entrySet()) {
            // Every other update will try to set an obsolete offset. We need to verify that such a case will not be accepted.
            successfulUpdate = !successfulUpdate;
            val existingOffset = e.getValue().getSegmentOffset();
            val segmentIndexOffset = keyCache.getSegmentIndexOffset(e.getKey().segmentId);
            long newOffset = existingOffset + 1;
            keyCache.updateSegmentIndexOffset(e.getKey().segmentId, Math.max(segmentIndexOffset, newOffset));

            if (successfulUpdate) {
                long updateResult = keyCache.includeExistingKey(e.getKey().segmentId, e.getKey().keyHash, newOffset);
                Assert.assertEquals("Unexpected result from includeExistingKey() for successful update.", newOffset, updateResult);
                e.setValue(new CacheBucketOffset(newOffset, false));
            } else {
                // Update this Hash's offset with a much higher one.
                val update = TableKeyBatch.update();
                update.add(newTableKey(rnd), e.getKey().keyHash, 1);
                long expectedOffset = keyCache.includeUpdateBatch(e.getKey().segmentId, update, segmentIndexOffset + 1).get(0);
                e.setValue(new CacheBucketOffset(expectedOffset, false));

                // Then verify that includeExistingKey won't modify it.
                long updateResult = keyCache.includeExistingKey(e.getKey().segmentId, e.getKey().keyHash, existingOffset + 1);
                Assert.assertEquals("Unexpected result from includeExistingKey() for obsolete update.", expectedOffset, updateResult);
            }
        }

        // Verify the cache, after updates.
        checkCache(expectedResult, keyCache);

        AssertExtensions.assertThrows(
                "includeExistingKey() accepted negative offset.",
                () -> keyCache.includeExistingKey(0, newSimpleHash(), -1L),
                ex -> ex instanceof IllegalArgumentException);
    }