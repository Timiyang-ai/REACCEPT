  @Test
  public void createTmpCheckpointFile() {
    UfsJournalFile file = UfsJournalFile.createTmpCheckpointFile(mLocation);
    Assert.assertEquals(UfsJournal.UNKNOWN_SEQUENCE_NUMBER, file.getStart());
    Assert.assertEquals(UfsJournal.UNKNOWN_SEQUENCE_NUMBER, file.getEnd());
    Assert.assertEquals(mLocation, file.getLocation());
    Assert.assertFalse(file.isCheckpoint());
    Assert.assertFalse(file.isIncompleteLog());
    Assert.assertFalse(file.isCompletedLog());
    Assert.assertTrue(file.isTmpCheckpoint());
  }