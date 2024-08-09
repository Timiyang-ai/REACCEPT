@Test
  public void reportOrphanedSpans_whenReporterDies() {
    PendingSpans map = new PendingSpans(endpoint, () -> 0, span ->
    {
      throw new RuntimeException("die!");
    }, new AtomicBoolean(true));

    // We drop the reference to the context, which means the next GC should attempt to flush it
    map.getOrCreate(context.toBuilder().build(), false);

    blockOnGC();

    // Sanity check that the referent trace context cleared due to GC
    assertThat(map.delegate.keySet()).extracting(o -> ((Reference) o).get())
        .hasSize(1)
        .containsNull();

    // The innocent caller isn't killed due to the exception in implicitly reporting GC'd spans
    map.remove(context);

    // However, the reference queue has been cleared.
    assertThat(map.delegate.keySet())
        .isEmpty();
  }