  @Test
  public void setNextContainerId() {
    mGenerator.setNextContainerId(TEST_ID);
    assertEquals(TEST_ID, mGenerator.getNewContainerId());
    assertEquals(TEST_ID + 1, mGenerator.getNewContainerId());
    assertEquals(TEST_ID + 2, mGenerator.getNewContainerId());
  }