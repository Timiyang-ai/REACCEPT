@Test
  public void generateFirstSegmentNameTest() {
    assertEquals("Did not get expected name", "", LogSegmentNameHelper.generateFirstSegmentName(false));
    String firstSegmentName = LogSegmentNameHelper.getName(0, 0);
    assertEquals("Did not get expected name", firstSegmentName, LogSegmentNameHelper.generateFirstSegmentName(true));
  }