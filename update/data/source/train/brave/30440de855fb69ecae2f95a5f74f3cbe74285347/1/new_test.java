@Test
  public void reportOrphanedSpans_whenReporterDies() throws Exception {
    init(new FinishedSpanHandler() {
      @Override public boolean handle(TraceContext context, MutableSpan span) {
        throw new RuntimeException();
      }
    });

    // We drop the reference to the context, which means the next GC should attempt to flush it
    pendingSpans.getOrCreate(context.toBuilder().build(), false);

    blockOnGC();

    // Sanity check that the referent trace context cleared due to GC
    assertThat(pendingSpans.delegate.keySet()).extracting(o -> ((Reference) o).get())
        .hasSize(1)
        .containsNull();

    // The innocent caller isn't killed due to the exception in implicitly reporting GC'd spans
    pendingSpans.remove(context);

    // However, the reference queue has been cleared.
    assertThat(pendingSpans.delegate.keySet())
        .isEmpty();
  }