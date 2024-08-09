@Test
  public void testShouldFireNow() {
    try {
      Date blockOutStartDate = new Date(System.currentTimeMillis());
      SimpleBlockoutTrigger blockOutTrigger = new SimpleBlockoutTrigger("blockOut1", blockOutStartDate, null, //$NON-NLS-1$
          -1, TIME.WEEK.time * 2, TIME.HOUR.time * 2);
      this.blockOutManager.addBlockout(blockOutTrigger);

      assertFalse(this.blockOutManager.shouldFireNow());

      this.blockOutManager.deleteBlockout(blockOutTrigger.getName());
      blockOutStartDate = new Date(System.currentTimeMillis() + TIME.HOUR.time);
      blockOutTrigger = new SimpleBlockoutTrigger("blockOut1", blockOutStartDate, null, //$NON-NLS-1$
          -1, TIME.WEEK.time * 2, TIME.HOUR.time * 2);
      this.blockOutManager.addBlockout(blockOutTrigger);

      assertTrue(this.blockOutManager.shouldFireNow());
    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
  }