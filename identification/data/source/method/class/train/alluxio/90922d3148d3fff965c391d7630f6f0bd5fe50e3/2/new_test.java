  @Test
  public void createCheckpointFile() {
    UfsJournalFile file = UfsJournalFile.createCheckpointFile(mLocation, 0x100);
    Assert.assertEquals(0, file.getStart());
    Assert.assertEquals(0x100, file.getEnd());
    Assert.assertEquals(mLocation, file.getLocation());
    Assert.assertTrue(file.isCheckpoint());
    Assert.assertFalse(file.isIncompleteLog());
    Assert.assertFalse(file.isCompletedLog());
    Assert.assertFalse(file.isTmpCheckpoint());
  }