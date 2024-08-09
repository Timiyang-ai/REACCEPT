  @Test
  public void format() throws Exception {
    mJournal.getUfs().create(UfsJournalFile.encodeCheckpointFileLocation(mJournal, 0x12).toString())
        .close();
    mJournal.getUfs()
        .create(UfsJournalFile.encodeTemporaryCheckpointFileLocation(mJournal).toString()).close();

    long start = 0x11;
    for (int i = 0; i < 10; i++) {
      String l =
          UfsJournalFile.encodeLogFileLocation(mJournal, start + i, start + i + 2).toString();
      mJournal.getUfs().create(l).close();
      start = start + i + 2;
    }
    String currentLog =
        UfsJournalFile.encodeLogFileLocation(mJournal, start, UfsJournal.UNKNOWN_SEQUENCE_NUMBER)
            .toString();
    mJournal.getUfs().create(currentLog).close();
    // Write a malformed log file which should be skipped.
    mJournal.getUfs()
        .create(UfsJournalFile.encodeLogFileLocation(mJournal, 0x10, 0x100).toString() + ".tmp")
        .close();

    mJournal.format();
    Assert.assertTrue(mJournal.isFormatted());
    UfsJournalSnapshot snapshot = UfsJournalSnapshot.getSnapshot(mJournal);
    Assert.assertTrue(snapshot.getCheckpoints().isEmpty());
    Assert.assertTrue(snapshot.getLogs().isEmpty());
    Assert.assertTrue(snapshot.getTemporaryCheckpoints().isEmpty());
  }