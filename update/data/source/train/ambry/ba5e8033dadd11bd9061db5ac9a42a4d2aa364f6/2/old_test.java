@Test
  public void generateFirstSegmentNameTest() {
    assertEquals("Did not get expected name", "", LogSegmentNameHelper.generateFirstSegmentName(1));
    String firstSegmentName = LogSegmentNameHelper.getName(0, 0);
    for (int i = 0; i < 10; i++) {
      long numSegments = Utils.getRandomLong(TestUtils.RANDOM, 1000) + 2;
      assertEquals("Did not get expected name", firstSegmentName,
          LogSegmentNameHelper.generateFirstSegmentName(numSegments));
    }
    int[] invalidNumSegments = {0, -1};
    for (int numSegments : invalidNumSegments) {
      try {
        LogSegmentNameHelper.generateFirstSegmentName(numSegments);
        fail("Should have failed to get the first segment name for [" + numSegments + "] segments");
      } catch (IllegalArgumentException e) {
        // expected. Nothing to do.
      }
    }
  }