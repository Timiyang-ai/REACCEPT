  @Test
  public void countMissing() {
    fillLargerColumn();
    column1.appendInternal(TimeColumnType.missingValueIndicator());
    column1.appendInternal(TimeColumnType.missingValueIndicator());
    assertEquals(3, column1.countMissing());
  }