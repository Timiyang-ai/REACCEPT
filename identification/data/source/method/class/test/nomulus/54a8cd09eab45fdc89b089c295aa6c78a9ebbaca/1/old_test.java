  @Test
  public void test_loadBucket_loadsTheBucket() {
    assertThat(loadBucket(getBucketKey(1))).isEqualTo(bucket);
  }