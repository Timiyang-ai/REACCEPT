  @Test
  public void create_WithNullLabelValueList() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage(CoreMatchers.equalTo("labelValues"));
    TimeSeries.create(null, Collections.<Point>emptyList(), TIMESTAMP_1);
  }