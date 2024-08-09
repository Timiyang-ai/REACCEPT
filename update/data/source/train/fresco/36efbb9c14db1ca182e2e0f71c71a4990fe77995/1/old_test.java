@Test
  public void testIsReusable() throws Exception {
    Bitmap b1 = mPool.alloc(12);
    assertTrue(mPool.isReusable(b1));
    Bitmap b2 = MockBitmapFactory.create(3, 4);
    assertTrue(mPool.isReusable(b2));
    Bitmap b3 = MockBitmapFactory.create(3, 4, Config.ARGB_4444);
    assertFalse(mPool.isReusable(b3));
    Bitmap b4 = MockBitmapFactory.create(3, 4);
    doReturn(true).when(b4).isRecycled();
    assertFalse(mPool.isReusable(b4));
    Bitmap b5 = MockBitmapFactory.create(3, 4);
    doReturn(false).when(b5).isMutable();
    assertFalse(mPool.isReusable(b5));
  }