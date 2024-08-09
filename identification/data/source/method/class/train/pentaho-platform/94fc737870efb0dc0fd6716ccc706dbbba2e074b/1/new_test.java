@Test
  public void testWillBlockSchedules() {
    try {
      Calendar trueBlockOutStartDate = new GregorianCalendar(2013, Calendar.JANUARY, 7);
      SimpleBlockoutTrigger trueBlockOutTrigger = new SimpleBlockoutTrigger(
          "blockOut", trueBlockOutStartDate.getTime(), null, //$NON-NLS-1$
          -1, TIME.WEEK.time * 2, TIME.HOUR.time * 2);

      Calendar falseBlockOutStartDate = new GregorianCalendar(2013, Calendar.JANUARY, 8);
      SimpleBlockoutTrigger falseBlockOutTrigger = new SimpleBlockoutTrigger(
          "blockOut", falseBlockOutStartDate.getTime(), null, //$NON-NLS-1$
          -1, TIME.WEEK.time * 2, TIME.HOUR.time * 2);

      Calendar scheduleStartDate = new GregorianCalendar(2013, Calendar.JANUARY, 7, 1, 0, 0);
      SimpleTrigger scheduleTrigger = new SimpleTrigger("trueSchedule", "SCHEDULES", scheduleStartDate.getTime(), null,
          -1, TIME.WEEK.time);
      scheduleJob(scheduleTrigger);

      assertEquals(1, this.blockOutManager.willBlockSchedules(trueBlockOutTrigger).size());
      assertEquals(0, this.blockOutManager.willBlockSchedules(falseBlockOutTrigger).size());

      // Clean up
      deleteJob(scheduleTrigger);

    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
  }