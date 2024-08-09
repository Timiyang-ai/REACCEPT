  @Test
  public void createBlockId() {
    assertEquals(16797216L, BlockId.createBlockId(1, 20000L));
    assertEquals(20000L, BlockId.createBlockId(0, 20000L));
    assertEquals(2071248101952L, BlockId.createBlockId(123456, 123456L));
  }