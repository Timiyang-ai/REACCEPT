@Test
  public void testCreateTimeOfDay() throws Exception {
    int hour = rand.nextInt(24);
    int minute = rand.nextInt(60);
    int second = rand.nextInt(60);
    String timeString =
        Integer.toString(hour) + ":" + Integer.toString(minute) + ":" + Integer.toString(second);

    TimeOfDay time = TransferJobUtils.createTimeOfDay(timeString);

    assertEquals(time,
        TimeOfDay.class.newInstance().setHours(hour).setMinutes(minute).setSeconds(second));
  }