  @Test
  @Parameters(method = "getNormalizeData")
  @TestCaseName("normalizePattern{0}")
  public void normalize(String testName, String query, String expected) throws Exception {
    assertEquals(expected, QueryNormalizer.normalize(query));
  }