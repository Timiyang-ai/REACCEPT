  @Test
  public void createPoint() {
    assertThat(StackdriverExportUtils.createPoint(POINT, null))
        .isEqualTo(
            com.google.monitoring.v3.Point.newBuilder()
                .setInterval(
                    TimeInterval.newBuilder()
                        .setEndTime(StackdriverExportUtils.convertTimestamp(TIMESTAMP))
                        .build())
                .setValue(StackdriverExportUtils.createTypedValue(VALUE_DOUBLE))
                .build());
  }