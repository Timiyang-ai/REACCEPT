  @Test public void getTraces_filteringMatchesMostRecentTraces() throws IOException {
    List<Endpoint> endpoints = IntStream.rangeClosed(1, 10)
      .mapToObj(i -> Endpoint.newBuilder().serviceName("service" + i).ip("127.0.0.1").build())
      .collect(Collectors.toList());

    long gapBetweenSpans = 100;
    List<Span> earlySpans =
      IntStream.rangeClosed(1, 10).mapToObj(i -> Span.newBuilder().name("early")
        .traceId(Integer.toHexString(i)).id(Integer.toHexString(i))
        .timestamp((TODAY - i) * 1000).duration(1L)
        .localEndpoint(endpoints.get(i - 1)).build()).collect(toList());

    List<Span> lateSpans = IntStream.rangeClosed(1, 10).mapToObj(i -> Span.newBuilder().name("late")
      .traceId(Integer.toHexString(i + 10)).id(Integer.toHexString(i + 10))
      .timestamp((TODAY + gapBetweenSpans - i) * 1000).duration(1L)
      .localEndpoint(endpoints.get(i - 1)).build()).collect(toList());

    storage.accept(earlySpans).execute();
    storage.accept(lateSpans).execute();

    List<Span>[] earlyTraces =
      earlySpans.stream().map(Collections::singletonList).toArray(List[]::new);
    List<Span>[] lateTraces =
      lateSpans.stream().map(Collections::singletonList).toArray(List[]::new);

    //sanity checks
    assertThat(storage.getTraces(requestBuilder().serviceName("service1").build()).execute())
      .containsExactly(lateTraces[0], earlyTraces[0]);

    assertThat(storage.getTraces(requestBuilder().build()).execute())
      .hasSize(20);

    assertThat(storage.getTraces(requestBuilder()
      .limit(10).build()).execute())
      .containsExactly(lateTraces);

    assertThat(storage.getTraces(requestBuilder()
      .endTs(TODAY + gapBetweenSpans).lookback(gapBetweenSpans).build()).execute())
      .containsExactly(lateTraces);

    assertThat(storage.getTraces(requestBuilder()
      .endTs(TODAY).build()).execute())
      .containsExactly(earlyTraces);
  }