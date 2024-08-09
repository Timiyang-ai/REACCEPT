static UfsJournalFile createTmpCheckpointFile(URI location) {
    return new UfsJournalFile(location, UfsJournal.UNKNOWN_SEQUENCE_NUMBER,
        UfsJournal.UNKNOWN_SEQUENCE_NUMBER, false);
  }