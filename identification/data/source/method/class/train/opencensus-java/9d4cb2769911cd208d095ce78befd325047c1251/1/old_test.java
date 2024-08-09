  @Test
  public void convertTimestamp() {
    Timestamp censusTimestamp1 = Timestamp.create(100, 3000);
    assertThat(StackdriverExportUtils.convertTimestamp(censusTimestamp1))
        .isEqualTo(
            com.google.protobuf.Timestamp.newBuilder().setSeconds(100).setNanos(3000).build());

    // Stackdriver doesn't allow negative values, instead it will replace the negative values
    // by returning a default instance.
    Timestamp censusTimestamp2 = Timestamp.create(-100, 3000);
    assertThat(StackdriverExportUtils.convertTimestamp(censusTimestamp2))
        .isEqualTo(com.google.protobuf.Timestamp.newBuilder().build());
  }