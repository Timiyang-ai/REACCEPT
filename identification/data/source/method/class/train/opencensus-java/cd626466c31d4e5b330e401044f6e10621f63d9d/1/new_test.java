  @Test
  public void createBucketOptions() {
    assertThat(StackdriverExportUtils.createBucketOptions(BUCKET_OPTIONS))
        .isEqualTo(
            BucketOptions.newBuilder()
                .setExplicitBuckets(
                    Explicit.newBuilder().addAllBounds(Arrays.asList(0.0, 1.0, 3.0, 5.0)))
                .build());
  }