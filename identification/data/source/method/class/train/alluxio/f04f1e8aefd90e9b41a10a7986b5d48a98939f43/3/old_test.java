  @Test
  public void insert() {
    // No bucket should expire.
    List<TtlBucket> expired = getSortedExpiredBuckets(BUCKET1_START);
    Assert.assertTrue(expired.isEmpty());

    mBucketList.insert(BUCKET1_FILE1);
    // The first bucket should expire.
    expired = getSortedExpiredBuckets(BUCKET1_END);
    assertExpired(expired, 0, BUCKET1_FILE1);

    mBucketList.insert(BUCKET1_FILE2);
    // Only the first bucket should expire.
    for (long end = BUCKET2_START; end < BUCKET2_END; end++) {
      expired = getSortedExpiredBuckets(end);
      assertExpired(expired, 0, BUCKET1_FILE1, BUCKET1_FILE2);
    }

    mBucketList.insert(BUCKET2_FILE);
    // All buckets should expire.
    expired = getSortedExpiredBuckets(BUCKET2_END);
    assertExpired(expired, 0, BUCKET1_FILE1, BUCKET1_FILE2);
    assertExpired(expired, 1, BUCKET2_FILE);
  }