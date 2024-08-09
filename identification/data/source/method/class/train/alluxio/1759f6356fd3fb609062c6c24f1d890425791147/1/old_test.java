  @Test
  public void recordTest() {
    TimeSeries timeSeries = new TimeSeries();
    timeSeries.record(mBase + 10L * Constants.SECOND_NANO);
    timeSeries.record(mBase + 10L * Constants.SECOND_NANO + 1);
    timeSeries.record(mBase + 10L * Constants.SECOND_NANO + 2);
    timeSeries.record((mBase + 13L * Constants.SECOND_NANO));

    Assert.assertEquals(3, timeSeries.get(mBase + Constants.SECOND_NANO * 10L + 3));
    Assert.assertEquals(1, timeSeries.get(mBase + Constants.SECOND_NANO * 13L));
    Assert.assertEquals(0, timeSeries.get(mBase + Constants.SECOND_NANO * 12L));
    Assert.assertEquals(0, timeSeries.get(mBase + Constants.SECOND_NANO * 11L));
  }