  @Test
  public void getPresetReminder () {
    long mockedNextMinute = 1497315847L;
    Long testReminder = null;
    PowerMockito.stub(PowerMockito.method(DateUtils.class, "getNextMinute")).toReturn(mockedNextMinute);
    junit.framework.Assert.assertEquals(mockedNextMinute, DateUtils.getPresetReminder(testReminder));
  }