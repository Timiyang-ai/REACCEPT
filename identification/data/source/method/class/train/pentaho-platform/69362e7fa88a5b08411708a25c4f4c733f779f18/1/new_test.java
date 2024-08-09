@Test
  public void testWillFire() throws Exception {
    Calendar blockOutStartDate = new GregorianCalendar(2013, Calendar.JANUARY, 7);
    IBlockoutTrigger blockOutTrigger = new SimpleBlockoutTrigger("blockOut", blockOutStartDate.getTime(), null, //$NON-NLS-1$
        -1, TIME.WEEK.time, TIME.HOUR.time * 2);

    /*
     * Simple Schedule Triggers
     */
    Calendar scheduleStartDate = new GregorianCalendar(2013, Calendar.JANUARY, 7, 1, 0, 0);
    Trigger trueScheduleTrigger = new SimpleTrigger("trueSchedule", "SCHEDULES", scheduleStartDate.getTime(), //$NON-NLS-1$ //$NON-NLS-2$
        null, -1, TIME.DAY.time);

    Trigger falseScheduleTrigger = new SimpleTrigger("falseSchedule", "SCHEDULES", scheduleStartDate.getTime(), //$NON-NLS-1$ //$NON-NLS-2$
        null, -1, TIME.WEEK.time);

    this.blockOutManager.addBlockout(blockOutTrigger);
    assertTrue(this.blockOutManager.willFire(trueScheduleTrigger));
    assertFalse(this.blockOutManager.willFire(falseScheduleTrigger));

    /*
     * Complex Schedule Triggers
     */
    Trigger trueComplexScheduleTrigger = new CronTrigger("trueCronTrigger", "SCHEDULES", "cronJob", "CRONJOBS", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        scheduleStartDate.getTime(), null, "0 0 1 ? * MON-TUE"); //$NON-NLS-1$

    Trigger falseComplexScheduleTrigger = new CronTrigger("falseCronTrigger", "SCHEDULES", "cronJob", "CRONJOBS", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        scheduleStartDate.getTime(), null, "0 0 1 ? * MON"); //$NON-NLS-1$

    assertTrue(this.blockOutManager.willFire(trueComplexScheduleTrigger));
    assertFalse(this.blockOutManager.willFire(falseComplexScheduleTrigger));

    /*
     * Complex Block out
     */
    this.blockOutManager.deleteBlockout(((Trigger) blockOutTrigger).getName());
    blockOutTrigger = new CronBlockOutTrigger("blockOut", blockOutStartDate.getTime(), null, "0 0 0 ? * MON", null, //$NON-NLS-1$ //$NON-NLS-2$
        TIME.HOUR.time * 2);
    this.blockOutManager.addBlockout(blockOutTrigger);

    assertTrue(this.blockOutManager.willFire(trueScheduleTrigger));
    assertFalse(this.blockOutManager.willFire(falseScheduleTrigger));
    assertTrue(this.blockOutManager.willFire(trueComplexScheduleTrigger));
    assertFalse(this.blockOutManager.willFire(falseComplexScheduleTrigger));

  }