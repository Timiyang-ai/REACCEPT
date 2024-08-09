@Test
  public void testWillFire() {
    try {
      Calendar blockOutStartDate = new GregorianCalendar(2013, Calendar.JANUARY, 7);
      SimpleBlockoutTrigger blockOutTrigger = new SimpleBlockoutTrigger("blockOut", blockOutStartDate.getTime(), null, //$NON-NLS-1$
          -1, TIME.WEEK.time * 2, TIME.HOUR.time * 2);

      Calendar scheduleStartDate = new GregorianCalendar(2013, Calendar.JANUARY, 7, 1, 0, 0);
      SimpleTrigger trueScheduleTrigger = new SimpleTrigger("trueSchedule", "SCHEDULES", scheduleStartDate.getTime(),
          null, -1, TIME.WEEK.time);
      scheduleJob(trueScheduleTrigger);

      SimpleTrigger falseScheduleTrigger = new SimpleTrigger("falseSchedule", "SCHEDULES", scheduleStartDate.getTime(),
          null, -1, TIME.WEEK.time * 2);
      scheduleJob(falseScheduleTrigger);

      this.blockOutManager.addBlockout(blockOutTrigger);
      assertTrue(this.blockOutManager.willFire(trueScheduleTrigger));
      assertFalse(this.blockOutManager.willFire(falseScheduleTrigger));

      // Clean up
      deleteJob(falseScheduleTrigger);
      deleteJob(trueScheduleTrigger);

    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
  }