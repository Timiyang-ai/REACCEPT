  @Test
  public void setParentHash() {
    genesisBlock.setParentHash("0x1234");
    Assert.assertEquals("0x1234", genesisBlock.getParentHash());
  }