  @Test public void localServiceName() {
    tracer = Tracing.newBuilder().localServiceName("my-foo").build().tracer();

    assertThat(tracer).extracting(
      "finishedSpanHandler.delegate.converter.localEndpoint.serviceName")
      .isEqualTo("my-foo");
  }