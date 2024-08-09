public synchronized void evictAll() throws IOException {
    initialize();
    // Copying for safe iteration.
    for (Entry entry : lruEntries.values().toArray(new Entry[lruEntries.size()])) {
      removeEntry(entry);
    }
    mostRecentTrimFailed = false;
  }