@Test
  public void getTraces_duration() {
    setupDurationData();

    QueryRequest.Builder q = QueryRequest.builder().lookback(DAY); // instead of since epoch
    QueryRequest query;

    // Min duration is inclusive and is applied by service.
    query = q.serviceName("service1").minDuration(200_000L).build();
    assertThat(store().getTraces(query)).extracting(t -> t.get(0).traceId)
      .containsExactly(1L);

    query = q.serviceName("service3").minDuration(200_000L).build();
    assertThat(store().getTraces(query)).extracting(t -> t.get(0).traceId)
      .containsExactly(2L);

    // Duration bounds aren't limited to root spans: they apply to all spans by service in a trace
    query = q.serviceName("service2").minDuration(50_000L).maxDuration(150_000L).build();
    assertThat(store().getTraces(query)).extracting(t -> t.get(0).traceId)
      .containsExactly(3L, 2L, 1L); // service2 root of trace 3, but middle of 1 and 2.

    // Span name should apply to the duration filter
    query = q.serviceName("service2").spanName("zip").maxDuration(50_000L).build();
    assertThat(store().getTraces(query)).extracting(t -> t.get(0).traceId)
      .containsExactly(3L);

    // Max duration should filter our longer spans from the same service
    query = q.serviceName("service2").minDuration(50_000L).maxDuration(50_000L).build();
    assertThat(store().getTraces(query)).extracting(t -> t.get(0).traceId)
      .containsExactly(3L);
  }