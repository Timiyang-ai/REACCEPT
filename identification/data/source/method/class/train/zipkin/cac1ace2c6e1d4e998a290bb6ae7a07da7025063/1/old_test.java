@Test
  public void getTraces_duration() {
    Endpoint service1 = Endpoint.create("service1", 127 << 24 | 1);
    Endpoint service2 = Endpoint.create("service2", 127 << 24 | 2);
    Endpoint service3 = Endpoint.create("service3", 127 << 24 | 3);

    BinaryAnnotation.Builder component = BinaryAnnotation.builder().key(LOCAL_COMPONENT).value("archiver");
    BinaryAnnotation archiver1 = component.endpoint(service1).build();
    BinaryAnnotation archiver2 = component.endpoint(service2).build();
    BinaryAnnotation archiver3 = component.endpoint(service3).build();

    Span targz = Span.builder().traceId(1L).id(1L)
        .name("targz").timestamp(TODAY * 1000 + 100L).duration(200_000L).addBinaryAnnotation(archiver1).build();
    Span tar = Span.builder().traceId(1L).id(2L).parentId(1L)
        .name("tar").timestamp(TODAY * 1000 + 200L).duration(150_000L).addBinaryAnnotation(archiver2).build();
    Span gz = Span.builder().traceId(1L).id(3L).parentId(1L)
        .name("gz").timestamp(TODAY * 1000 + 250L).duration(50_000L).addBinaryAnnotation(archiver3).build();
    Span zip = Span.builder().traceId(3L).id(3L)
        .name("zip").timestamp(TODAY * 1000 + 130L).duration(50_000L).addBinaryAnnotation(archiver2).build();

    List<Span> trace1 = asList(targz, tar, gz);
    List<Span> trace2 = asList(
        targz.toBuilder().traceId(2L).timestamp(TODAY * 1000 + 110L).binaryAnnotations(asList(archiver3)).build(),
        tar.toBuilder().traceId(2L).timestamp(TODAY * 1000 + 210L).binaryAnnotations(asList(archiver2)).build(),
        gz.toBuilder().traceId(2L).timestamp(TODAY * 1000 + 260L).binaryAnnotations(asList(archiver1)).build());
    List<Span> trace3 = asList(zip);

    accept(trace1.toArray(new Span[0]));
    accept(trace2.toArray(new Span[0]));
    accept(trace3.toArray(new Span[0]));

    long lookback = 12L * 60 * 60 * 1000; // 12hrs, instead of 7days
    long endTs = TODAY + 1; // greater than all timestamps above
    QueryRequest.Builder q = QueryRequest.builder().serviceName("service1").lookback(lookback).endTs(endTs);

    // Min duration is inclusive and is applied by service.
    assertThat(store().getTraces(q.serviceName("service1").minDuration(targz.duration).build()))
        .containsExactly(trace1);

    assertThat(store().getTraces(q.serviceName("service3").minDuration(targz.duration).build()))
        .containsExactly(trace2);

    // Duration bounds aren't limited to root spans: they apply to all spans by service in a trace
    assertThat(store().getTraces(q.serviceName("service2").minDuration(zip.duration).maxDuration(tar.duration).build()))
        .containsExactly(trace3, trace2, trace1); // service2 is in the middle of trace1 and 2, but root of trace3

    // Span name should apply to the duration filter
    assertThat(
        store().getTraces(q.serviceName("service2").spanName("zip").maxDuration(zip.duration).build()))
        .containsExactly(trace3);

    // Max duration should filter our longer spans from the same service
    assertThat(store().getTraces(q.serviceName("service2").minDuration(gz.duration).maxDuration(zip.duration).build()))
        .containsExactly(trace3);
  }