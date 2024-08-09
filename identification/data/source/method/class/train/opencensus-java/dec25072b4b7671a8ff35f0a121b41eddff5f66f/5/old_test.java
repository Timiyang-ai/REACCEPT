  @Test
  public void createTypedValue() {
    assertThat(StackdriverExportUtils.createTypedValue(DOUBLE_VALUE))
        .isEqualTo(TypedValue.newBuilder().setDoubleValue(1.1).build());
    assertThat(StackdriverExportUtils.createTypedValue(LONG_VALUE))
        .isEqualTo(TypedValue.newBuilder().setInt64Value(10000).build());
    assertThat(StackdriverExportUtils.createTypedValue(DISTRIBUTION_VALUE))
        .isEqualTo(
            TypedValue.newBuilder()
                .setDistributionValue(StackdriverExportUtils.createDistribution(DISTRIBUTION))
                .build());
  }