@Test
  public void reportOrphanedSpans_afterGC() throws Exception {
    TraceContext context1 = context.toBuilder().traceId(1).spanId(1).build();
    pendingSpans.getOrCreate(context1, false);
    TraceContext context2 = context.toBuilder().traceId(2).spanId(2).build();
    pendingSpans.getOrCreate(context2, false);
    TraceContext context3 = context.toBuilder().traceId(3).spanId(3).build();
    pendingSpans.getOrCreate(context3, false);
    TraceContext context4 = context.toBuilder().traceId(4).spanId(4).build();
    pendingSpans.getOrCreate(context4, false);
    // ensure sampled local spans are not reported when orphaned unless they are also sampled remote
    TraceContext context5 =
        context.toBuilder().spanId(5).sampledLocal(true).sampled(false).build();
    pendingSpans.getOrCreate(context5, false);

    int initialClockVal = clock.get();

    // By clearing strong references in this test, we are left with the weak ones in the map
    context1 = context2 = context5 = null;
    blockOnGC();

    // After GC, we expect that the weak references of context1 and context2 to be cleared
    assertThat(pendingSpans.delegate.keySet()).extracting(o -> ((Reference) o).get())
        .containsExactlyInAnyOrder(null, null, context3, context4, null);

    pendingSpans.reportOrphanedSpans();

    // After reporting, we expect no the weak references of null
    assertThat(pendingSpans.delegate.keySet()).extracting(o -> ((Reference) o).get())
        .containsExactlyInAnyOrder(context3, context4);

    // We also expect only the sampled (remote) spans to have been reported with a flush annotation
    assertThat(spans).flatExtracting(Span::id)
        .containsExactlyInAnyOrder("0000000000000001", "0000000000000002");
    assertThat(spans).flatExtracting(Span::annotations).extracting(Annotation::value)
        .containsExactly("brave.flush", "brave.flush");

    // we also expect the clock to have been called only once
    assertThat(spans).flatExtracting(Span::annotations).extracting(Annotation::timestamp)
        .allSatisfy(t -> assertThat(t).isEqualTo((initialClockVal + 1) * 1000));
  }