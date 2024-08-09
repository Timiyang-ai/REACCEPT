  @Test
  public void setTimestamp() {
    genesisBlock.setTimestamp("1234");
    Assert.assertEquals("1234", genesisBlock.getTimestamp());
  }