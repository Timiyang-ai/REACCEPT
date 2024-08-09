  @Test
  public void getRandomNonNegativeLong() throws Exception {
    long first = IdUtils.getRandomNonNegativeLong();
    long second = IdUtils.getRandomNonNegativeLong();
    assertTrue(first >= 0);
    assertTrue(second >= 0);
    assertTrue(first != second);
  }