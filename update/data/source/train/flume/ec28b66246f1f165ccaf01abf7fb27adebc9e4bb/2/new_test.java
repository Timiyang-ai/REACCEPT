@Test
  public void testRoundDownTimeStampMinutes() {
    Calendar cal = BASE_CALENDAR_WITH_DEFAULT_TIMEZONE;
    Calendar cal2 = createCalendar(2012, 5, 15, 15, 10, 0, 0, null);

    long timeToVerify = cal2.getTimeInMillis();
    long ret = TimestampRoundDownUtil.roundDownTimeStampMinutes(cal.getTimeInMillis(), 5);
    System.out.println("Cal 1: " + cal.toString());
    System.out.println("Cal 2: " + cal2.toString());
    Assert.assertEquals(timeToVerify, ret);
  }