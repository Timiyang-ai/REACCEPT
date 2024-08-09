@Test
  public void hardDeleteTest() throws InterruptedException, IOException, StoreException {
    properties.put("store.deleted.message.retention.days", Integer.toString(1));
    properties.put("store.hard.delete.bytes.per.sec", Integer.toString(Integer.MAX_VALUE / 10));
    reloadIndex(false);
    index.hardDeleter.enabled.set(true);
    assertFalse("Hard delete did work even though no message is past retention time", index.hardDeleter.hardDelete());
    // IndexSegment still uses real time so advance time so that it goes 2 days past the real time.
    advanceTime(SystemTime.getInstance().milliseconds() + 2 * Time.MsPerSec * Time.SecsPerDay);
    assertTrue("Hard delete did not do any work", index.hardDeleter.hardDelete());
    long expectedProgress = index.getAbsolutePositionInLogForOffset(logOrder.lastKey());
    assertEquals("Hard delete did not make expected progress", expectedProgress, index.hardDeleter.getProgress());
    verifyEntriesForHardDeletes(deletedKeys);
  }