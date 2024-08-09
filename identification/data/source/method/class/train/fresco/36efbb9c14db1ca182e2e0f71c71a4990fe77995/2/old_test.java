@Test
  public void testGetBucketedSizeForValue() throws Exception {
    Bitmap bitmap1 = mPool.alloc(12);
    Bitmap bitmap2 = mPool.alloc(56);
    Bitmap bitmap3 = MockBitmapFactory.create(7, 8, Config.ARGB_4444);

    assertEquals(12, (int) mPool.getBucketedSizeForValue(bitmap1));
    assertEquals(56, (int) mPool.getBucketedSizeForValue(bitmap2));
    assertEquals(56, (int) mPool.getBucketedSizeForValue(bitmap3));
  }