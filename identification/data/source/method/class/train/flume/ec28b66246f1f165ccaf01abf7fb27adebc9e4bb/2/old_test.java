@Test
  public void testRoundDownTimeStampSeconds() {
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(2012, 5, 15, 15, 12, 54);
    cal.set(Calendar.MILLISECOND, 20);
    Calendar cal2 = Calendar.getInstance();
    cal2.clear();
    cal2.set(2012, 5, 15, 15, 12, 0);
    cal2.set(Calendar.MILLISECOND, 0);
    long timeToVerify = cal2.getTimeInMillis();
    long ret = TimestampRoundDownUtil.roundDownTimeStampSeconds(cal.getTimeInMillis(), 60);
    System.out.println("Cal 1: " + cal.toString());
    System.out.println("Cal 2: " + cal2.toString());
    Assert.assertEquals(timeToVerify, ret);
  }