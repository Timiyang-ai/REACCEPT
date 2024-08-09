public synchronized void start(
      ImmutableSet<ProfilerTask> profiledTasks,
      OutputStream stream,
      Format format,
      String productName,
      String outputBase,
      UUID buildID,
      boolean recordAllDurations,
      Clock clock,
      long execStartTimeNanos,
      boolean enabledCpuUsageProfiling,
      boolean slimProfile,
      boolean enableJsonMetadata)
      throws IOException {
    Preconditions.checkState(!isActive(), "Profiler already active");
    initHistograms();

    this.profiledTasks = profiledTasks;
    this.clock = clock;

    // sanity check for current limitation on the number of supported types due
    // to using enum.ordinal() to store them instead of EnumSet for performance reasons.
    Preconditions.checkState(TASK_COUNT < 256,
        "The profiler implementation supports only up to 255 different ProfilerTask values.");

    // reset state for the new profiling session
    taskId.set(0);
    this.recordAllDurations = recordAllDurations;
    this.taskStack = new TaskStack();
    FileWriter writer = null;
    if (stream != null && format != null) {
      switch (format) {
        case BINARY_BAZEL_FORMAT:
          writer =
              new BinaryFormatWriter(
                  stream,
                  execStartTimeNanos,
                  String.format(
                      "%s profile for %s at %s, build ID: %s",
                      productName, outputBase, new Date(), buildID));
          break;
        case JSON_TRACE_FILE_FORMAT:
          writer =
              new JsonTraceFileWriter(
                  stream, execStartTimeNanos, slimProfile, enableJsonMetadata, outputBase, buildID);
          break;
        case JSON_TRACE_FILE_COMPRESSED_FORMAT:
          writer =
              new JsonTraceFileWriter(
                  new GZIPOutputStream(stream),
                  execStartTimeNanos,
                  slimProfile,
                  enableJsonMetadata,
                  outputBase,
                  buildID);
      }
      writer.start();
    }
    this.writerRef.set(writer);

    // activate profiler
    profileStartTime = execStartTimeNanos;

    if (enabledCpuUsageProfiling) {
      cpuUsageThread = new CollectLocalCpuUsage();
      cpuUsageThread.setDaemon(true);
      cpuUsageThread.start();
    }
  }