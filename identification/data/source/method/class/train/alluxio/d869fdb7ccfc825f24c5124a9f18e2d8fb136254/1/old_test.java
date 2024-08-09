@Test
  public void getLengthTest() throws Exception {
    Assert.assertEquals(TEST_BLOCK_SIZE, mReader.getLength());
  }