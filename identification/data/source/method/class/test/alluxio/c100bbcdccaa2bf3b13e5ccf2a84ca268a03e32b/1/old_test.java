  @Test
  public void compareTo() {
    TtlBucket firstBucket = new TtlBucket(0);
    TtlBucket secondBucket = new TtlBucket(0);
    TtlBucket thirdBucket = new TtlBucket(1);
    TtlBucket fourthBucket = new TtlBucket(2);

    Assert.assertEquals(0, firstBucket.compareTo(firstBucket));
    Assert.assertEquals(0, firstBucket.compareTo(secondBucket));
    Assert.assertEquals(0, secondBucket.compareTo(firstBucket));
    Assert.assertEquals(-1, firstBucket.compareTo(thirdBucket));
    Assert.assertEquals(1, fourthBucket.compareTo(firstBucket));
  }