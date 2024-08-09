  @Test
  public void createDistribution() {
    StackdriverExportUtils.setCachedProjectIdForExemplar(null);
    assertThat(StackdriverExportUtils.createDistribution(DISTRIBUTION))
        .isEqualTo(
            Distribution.newBuilder()
                .setCount(3)
                .setMean(0.6666666666666666)
                .setBucketOptions(StackdriverExportUtils.createBucketOptions(BUCKET_OPTIONS))
                .addAllBucketCounts(Arrays.asList(0L, 3L, 1L, 2L, 4L))
                .setSumOfSquaredDeviation(14)
                .addAllExemplars(
                    Arrays.<Distribution.Exemplar>asList(
                        Distribution.Exemplar.newBuilder()
                            .setValue(1.2)
                            .setTimestamp(StackdriverExportUtils.convertTimestamp(TIMESTAMP_2))
                            .addAttachments(
                                Any.newBuilder()
                                    .setTypeUrl(
                                        StackdriverExportUtils.EXEMPLAR_ATTACHMENT_TYPE_STRING)
                                    .setValue(ByteString.copyFromUtf8("value"))
                                    .build())
                            .build(),
                        Distribution.Exemplar.newBuilder()
                            .setValue(5.6)
                            .setTimestamp(StackdriverExportUtils.convertTimestamp(TIMESTAMP_3))
                            // Cached project ID is set to null, so no SpanContext attachment will
                            // be created.
                            .build()))
                .build());
  }