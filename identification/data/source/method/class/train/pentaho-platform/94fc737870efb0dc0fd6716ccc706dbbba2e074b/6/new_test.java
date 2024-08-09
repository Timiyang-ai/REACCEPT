@Test
  public void testAddBlockout() {
    SimpleBlockoutTrigger trigger = new SimpleBlockoutTrigger("blockout", new Date(), null, -1, 1000000, 50000); //$NON-NLS-1$
    try {
      blockOutManager.addBlockout(trigger);
    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
    try {
      assertEquals(blockOutManager.getBlockout("blockout"), trigger); //$NON-NLS-1$
    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
  }