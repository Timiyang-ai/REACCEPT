public static UfsJournalFile createCheckpointFile(URI location, long end) {
    return new UfsJournalFile(location, 0, end, true);
  }