public synchronized void start(
      ProfiledTaskKinds profiledTaskKinds,
      OutputStream stream,
      Format format,
      String comment,
      boolean recordAllDurations,
      Clock clock,
      long execStartTimeNanos) {
    Preconditions.checkState(!isActive(), "Profiler already active");
    taskStack = new TaskStack();
    taskQueue = new LinkedBlockingDeque<>();
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
    this.saveException = null;
    this.writerThread = null;
    if (stream != null) {
      if (format == Format.BINARY_BAZEL_FORMAT) {
        BinaryFormatWriter writer =
            new BinaryFormatWriter(
                stream, taskQueue, profileStartTime, comment, (IOException e) -> abortWriting(e));
        // Start save thread
        writerThread = new Thread(writer);
        writerThread.start();
      }
    }

    // activate profiler
    profileStartTime = execStartTimeNanos;
  }