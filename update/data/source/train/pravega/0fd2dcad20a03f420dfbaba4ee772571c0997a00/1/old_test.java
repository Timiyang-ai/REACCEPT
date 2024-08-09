@Test
    public void testGroupByBucket() {
        int bucketCount = 5;
        int hashesPerBucket = 5;
        val hashToBuckets = new HashMap<UUID, TableBucket>();
        val bucketsToKeys = new HashMap<TableBucket, ArrayList<BucketUpdate.KeyUpdate>>();
        val rnd = new Random(0);
        for (int i = 0; i < bucketCount; i++) {
            val bucket = new TableBucket(UUID.randomUUID(), i);

            // Keep track of all KeyUpdates for this bucket.
            val keyUpdates = new ArrayList<BucketUpdate.KeyUpdate>();
            bucketsToKeys.put(bucket, keyUpdates);

            // Generate keys, and record them where needed.
            for (int j = 0; j < hashesPerBucket; j++) {
                byte[] key = new byte[KeyHasher.HASH_SIZE_BYTES * 4];
                keyUpdates.add(new BucketUpdate.KeyUpdate(new HashedArray(key), i * hashesPerBucket + j, true));
                rnd.nextBytes(key);
                hashToBuckets.put(KeyHashers.DEFAULT_HASHER.hash(key), bucket);
            }
        }

        // Group updates by bucket. Since we override locateBucket, we do not need a segment access, hence safe to pass null.
        val w = new CustomLocateBucketIndexer(KeyHashers.DEFAULT_HASHER, executorService(), hashToBuckets);
        val allKeyUpdates = new ArrayList<BucketUpdate.KeyUpdate>();
        bucketsToKeys.values().forEach(allKeyUpdates::addAll);
        val bucketUpdates = w.groupByBucket(allKeyUpdates, null, new TimeoutTimer(TIMEOUT)).join();

        Assert.assertEquals("Unexpected number of Bucket Updates.", bucketCount, bucketUpdates.size());
        for (BucketUpdate bu : bucketUpdates) {
            Assert.assertTrue("Not expecting Existing Keys to be populated.", bu.getExistingKeys().isEmpty());
            val expected = bucketsToKeys.get(bu.getBucket());
            Assert.assertNotNull("Found extra bucket.", expected);
            AssertExtensions.assertContainsSameElements("Unexpected updates grouped.",
                    expected, bu.getKeyUpdates(),
                    (u1, u2) -> u1.getKey().equals(u2.getKey()) && u1.getOffset() == u2.getOffset() ? 0 : 1);
        }
    }