@Test
  public void testCreateDate() throws Exception {
    int year = rand.nextInt(2000) + 1;
    int month = rand.nextInt(12) + 1;
    int day = rand.nextInt(30) + 1;
    String dateString =
        Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

    Date date = TransferJobUtils.createDate(dateString);

    assertEquals(date, Date.class.newInstance().setYear(year).setMonth(month).setDay(day));
  }