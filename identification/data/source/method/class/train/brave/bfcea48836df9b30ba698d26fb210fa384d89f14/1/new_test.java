  @Test public void spanReporter_getsLocalEndpointInfo() {
    String expectedLocalServiceName = "favistar", expectedLocalIp = "1.2.3.4";
    int expectedLocalPort = 80;

    List<Span> zipkinSpans = new ArrayList<>();
    Reporter<Span> spanReporter = span -> {
      assertThat(span.localServiceName()).isEqualTo(expectedLocalServiceName);
      assertThat(span.localEndpoint().ipv4()).isEqualTo(expectedLocalIp);
      assertThat(span.localEndpoint().portAsInt()).isEqualTo(expectedLocalPort);
      zipkinSpans.add(span);
    };

    try (Tracing tracing = Tracing.newBuilder()
      .localServiceName(expectedLocalServiceName)
      .localIp(expectedLocalIp)
      .localPort(expectedLocalPort)
      .spanReporter(spanReporter)
      .build()) {
      tracing.tracer().newTrace().start().finish();
    }

    assertThat(zipkinSpans).isNotEmpty(); // ensures the assertions passed.
  }