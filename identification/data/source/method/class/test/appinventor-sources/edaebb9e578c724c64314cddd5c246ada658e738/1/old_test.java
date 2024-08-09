  public void testDateValue() {
    // Check NullPointerException is thrown when the input string is null
    try {
      Calendar date = Dates.DateValue(null);
      fail();
    } catch (NullPointerException expected) {
    }

    // Empty string is an illegal argument
    try {
      Calendar date = Dates.DateValue("");
      fail();
    } catch (IllegalArgumentException expected) {
    }

    // Another illegal argument
    try {
      Calendar date = Dates.DateValue("abc");
      fail();
    } catch (IllegalArgumentException expected) {
    }

    // Input string with extra characters at the end is OK
    Dates.DateValue("04/21/2008 abc");
    Dates.DateValue("04/21/2008 09:29:48 abc");

    // Date only input string
    Calendar date = Dates.DateValue("04/21/2008");
    assertEquals(2008, date.get(GregorianCalendar.YEAR));
    assertEquals(21, date.get(GregorianCalendar.DAY_OF_MONTH));
    assertEquals(Calendar.APRIL, date.get(GregorianCalendar.MONTH));
    assertEquals(0, date.get(GregorianCalendar.HOUR_OF_DAY));
    assertEquals(0, date.get(GregorianCalendar.MINUTE));
    assertEquals(0, date.get(GregorianCalendar.SECOND));

    // Date and time input string
    date = Dates.DateValue("04/21/2008 09:29:48");
    assertEquals(2008, date.get(GregorianCalendar.YEAR));
    assertEquals(21, date.get(GregorianCalendar.DAY_OF_MONTH));
    assertEquals(Calendar.APRIL, date.get(GregorianCalendar.MONTH));
    assertEquals(9, date.get(GregorianCalendar.HOUR_OF_DAY));
    assertEquals(29, date.get(GregorianCalendar.MINUTE));
    assertEquals(48, date.get(GregorianCalendar.SECOND));
  }