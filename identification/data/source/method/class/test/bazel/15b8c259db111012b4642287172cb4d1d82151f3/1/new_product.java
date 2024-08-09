public synchronized void start(
      ProfiledTaskKinds profiledTaskKinds,
      OutputStream stream,
      Format format,
      String comment,
      boolean recordAllDurations,
      Clock clock,
      long execStartTimeNanos) {
    Preconditions.checkState(!isActive(), "Profiler already active");
    initHistograms();

    this.profiledTaskKinds = profiledTaskKinds;
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
    if (stream != null) {
      if (format == Format.BINARY_BAZEL_FORMAT) {
        writer = new BinaryFormatWriter(stream, profileStartTime, comment);
        writer.start();
      } else if (format == Format.JSON_TRACE_FILE_FORMAT) {
        writer = new JsonTraceFileWriter(stream, profileStartTime);
        writer.start();
      }
    }
    this.writerRef.set(writer);

    // activate profiler
    profileStartTime = execStartTimeNanos;
  }