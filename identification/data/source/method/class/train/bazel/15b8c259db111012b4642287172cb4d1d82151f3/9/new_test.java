  private ByteArrayOutputStream start(ImmutableSet<ProfilerTask> tasks, Profiler.Format format)
      throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    profiler.start(
        tasks,
        buffer,
        format,
        "test",
        "dummy_output_base",
        UUID.randomUUID(),
        false,
        BlazeClock.instance(),
        BlazeClock.nanoTime(),
        /* enabledCpuUsageProfiling= */ false,
        /* slimProfile= */ false,
        /* enableActionCountProfile= */ false);
    return buffer;
  }