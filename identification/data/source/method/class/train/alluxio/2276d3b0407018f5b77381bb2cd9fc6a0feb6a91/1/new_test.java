  @Test
  public void updateToRemovedBlock() {
    // remove a non-existing block
    mInfo.updateToRemovedBlock(true, 10L);
    assertTrue(mInfo.getToRemoveBlocks().isEmpty());
    // remove block 1
    mInfo.updateToRemovedBlock(true, 1L);
    assertTrue(mInfo.getToRemoveBlocks().contains(1L));
    // cancel the removal
    mInfo.updateToRemovedBlock(false, 1L);
    assertTrue(mInfo.getToRemoveBlocks().isEmpty());
    // actually remove 1 for real
    mInfo.updateToRemovedBlock(true, 1L);
    mInfo.removeBlock(1L);
    assertTrue(mInfo.getToRemoveBlocks().isEmpty());
  }