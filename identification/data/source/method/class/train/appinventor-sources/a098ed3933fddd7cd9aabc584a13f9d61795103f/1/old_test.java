  public void testFormatDate() {
    Calendar date = Dates.DateValue("04/21/2008 09:29:48");
    assertEquals("Apr 21, 2008", Dates.FormatDate(date,""));
  }