@Test
  public void testUpdateBlockout() {
    long INITIAL_BLOCK_DURATION = 50000l;
    long UPDATED_BLOCK_DURATION = 1000000l;
    SimpleBlockoutTrigger trigger = new SimpleBlockoutTrigger("updateBlockout", new Date(), null, -1, 1000000, INITIAL_BLOCK_DURATION); //$NON-NLS-1$
    try {
      blockOutManager.addBlockout(trigger);
      SimpleBlockoutTrigger updatedTrigger = new SimpleBlockoutTrigger("updateBlockout", new Date(), null, -1, 1000000, UPDATED_BLOCK_DURATION); //$NON-NLS-1$
      blockOutManager.updateBlockout(updatedTrigger.getName(), updatedTrigger);
      SimpleBlockoutTrigger retrievedTrigger = (SimpleBlockoutTrigger) blockOutManager.getBlockout(updatedTrigger.getName());
      assertEquals(UPDATED_BLOCK_DURATION, retrievedTrigger.getBlockDuration());
    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
  }