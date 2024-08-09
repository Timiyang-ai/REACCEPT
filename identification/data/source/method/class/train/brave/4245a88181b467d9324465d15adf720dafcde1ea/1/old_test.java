@Test
  public void reportOrphanedSpans_afterGC() throws Exception {
    TraceContext context1 = context.toBuilder().spanId(1).build();
    map.getOrCreate(context1);
    TraceContext context2 = context.toBuilder().spanId(2).build();
    map.getOrCreate(context2);
    TraceContext context3 = context.toBuilder().spanId(3).build();
    map.getOrCreate(context3);
    TraceContext context4 = context.toBuilder().spanId(4).build();
    map.getOrCreate(context4);

    // By clearing strong references in this test, we are left with the weak ones in the map
    context1 = context2 = null;
    blockOnGC();

    // After GC, we expect that the weak references of context1 and context2 to be cleared
    assertThat(map.delegate.keySet()).extracting(o -> ((Reference) o).get())
        .containsExactlyInAnyOrder(null, null, context3, context4);

    map.reportOrphanedSpans();

    // After reporting, we expect no the weak references of null
    assertThat(map.delegate.keySet()).extracting(o -> ((Reference) o).get())
        .containsExactlyInAnyOrder(context3, context4);

    // We also expect the spans to have been reported
    assertThat(spans).flatExtracting(s -> s.annotations).extracting(a -> a.value)
        .containsExactly("brave.flush", "brave.flush");
  }