  @Test
  public void remove() {
    mBucketList.insert(BUCKET1_FILE1);
    mBucketList.insert(BUCKET1_FILE2);
    mBucketList.insert(BUCKET2_FILE);

    List<TtlBucket> expired = getSortedExpiredBuckets(BUCKET1_END);
    assertExpired(expired, 0, BUCKET1_FILE1, BUCKET1_FILE2);

    mBucketList.remove(BUCKET1_FILE1);
    expired = getSortedExpiredBuckets(BUCKET1_END);
    // Only the first bucket should expire, and there should be only one BUCKET1_FILE2 in it.
    assertExpired(expired, 0, BUCKET1_FILE2);

    mBucketList.remove(BUCKET1_FILE2);
    expired = getSortedExpiredBuckets(BUCKET1_END);
    // Only the first bucket should expire, and there should be no files in it.
    assertExpired(expired, 0); // nothing in bucket 0.

    expired = getSortedExpiredBuckets(BUCKET2_END);
    // All buckets should expire.
    assertExpired(expired, 0); // nothing in bucket 0.
    assertExpired(expired, 1, BUCKET2_FILE);

    // Remove bucket 0.
    expired = getSortedExpiredBuckets(BUCKET1_END);
    mBucketList.removeBuckets(Sets.newHashSet(expired));

    expired = getSortedExpiredBuckets(BUCKET2_END);
    // The only remaining bucket is bucket 1, it should expire.
    assertExpired(expired, 0, BUCKET2_FILE);

    mBucketList.remove(BUCKET2_FILE);
    expired = getSortedExpiredBuckets(BUCKET2_END);
    assertExpired(expired, 0); // nothing in bucket.

    mBucketList.removeBuckets(Sets.newHashSet(expired));
    // No bucket should exist now.
    expired = getSortedExpiredBuckets(BUCKET2_END);
    Assert.assertEquals(0, expired.size());
  }