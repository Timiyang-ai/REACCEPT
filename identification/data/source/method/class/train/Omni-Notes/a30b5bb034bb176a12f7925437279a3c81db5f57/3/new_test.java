  @Test
  public void isFuture () {
    String nextMinute = String.valueOf(Calendar.getInstance().getTimeInMillis() + 60000);
    String previousMinute = String.valueOf(Calendar.getInstance().getTimeInMillis() - 60000);
    assertTrue(DateUtils.isFuture(nextMinute));
    assertFalse(DateUtils.isFuture(previousMinute));
  }