@Test public void getTraces_absentWhenNoTimestamp() throws IOException {
    // Index the service name but no timestamp of any sort
    accept(Span.newBuilder()
      .traceId(CLIENT_SPAN.traceId())
      .id(CLIENT_SPAN.id())
      .name(CLIENT_SPAN.name())
      .localEndpoint(CLIENT_SPAN.localEndpoint())
      .build()
    );

    assertThat(store().getTraces(
      requestBuilder().serviceName(CLIENT_SPAN.localServiceName()).build()
    ).execute()).isEmpty();

    assertThat(store().getTraces(
      requestBuilder()
        .serviceName(CLIENT_SPAN.localServiceName())
        .spanName(CLIENT_SPAN.remoteServiceName())
        .build()
    ).execute()).isEmpty();

    assertThat(store().getTraces(
      requestBuilder()
        .serviceName(CLIENT_SPAN.localServiceName())
        .spanName(CLIENT_SPAN.name())
        .build()
    ).execute()).isEmpty();

    // now store the timestamped span
    accept(CLIENT_SPAN);

    assertThat(store().getTraces(
      requestBuilder().serviceName(CLIENT_SPAN.localServiceName()).build()
    ).execute()).isNotEmpty();

    assertThat(store().getTraces(
      requestBuilder()
        .serviceName(CLIENT_SPAN.localServiceName())
        .remoteServiceName(CLIENT_SPAN.remoteServiceName())
        .build()
    ).execute()).isNotEmpty();

    assertThat(store().getTraces(
      requestBuilder()
        .serviceName(CLIENT_SPAN.localServiceName())
        .spanName(CLIENT_SPAN.name())
        .build()
    ).execute()).isNotEmpty();
  }