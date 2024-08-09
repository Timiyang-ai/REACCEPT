@Test
  public void testRoundDownTimeStampHours() {
    Calendar cal = BASE_CALENDAR_WITH_DEFAULT_TIMEZONE;
    Calendar cal2 = createCalendar(2012, 5, 15, 14, 0, 0, 0, null);

    long timeToVerify = cal2.getTimeInMillis();
    long ret = TimestampRoundDownUtil.roundDownTimeStampHours(cal.getTimeInMillis(), 2);
    System.out.println("Cal 1: " + ret);
    System.out.println("Cal 2: " + cal2.toString());
    Assert.assertEquals(timeToVerify, ret);
  }