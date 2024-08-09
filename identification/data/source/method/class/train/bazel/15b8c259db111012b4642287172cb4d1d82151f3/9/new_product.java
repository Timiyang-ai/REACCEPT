public synchronized void start(
      ProfiledTaskKinds profiledTaskKinds,
      OutputStream stream,
      String comment,
      boolean recordAllDurations,
      Clock clock,
      long execStartTimeNanos)
          throws IOException {
    Preconditions.checkState(!isActive(), "Profiler already active");
    taskStack = new TaskStack();
    taskQueue = new ConcurrentLinkedQueue<>();
    describer = new ObjectDescriber();

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
    if (stream != null) {
      this.timer = new Timer("ProfilerTimer", true);
      // Wrapping deflater stream in the buffered stream proved to reduce CPU consumption caused by
      // the save() method. Values for buffer sizes were chosen by running small amount of tests
      // and identifying point of diminishing returns - but I have not really tried to optimize
      // them.
      this.out = new DataOutputStream(new BufferedOutputStream(new DeflaterOutputStream(
          stream, new Deflater(Deflater.BEST_SPEED, false), 65536), 262144));

      this.out.writeInt(MAGIC); // magic
      this.out.writeInt(VERSION); // protocol_version
      this.out.writeUTF(comment);
      // ProfileTask.values() method sorts enums using their ordinal() value, so
      // there there is no need to store ordinal() value for each entry.
      this.out.writeInt(TASK_COUNT);
      for (ProfilerTask type : ProfilerTask.values()) {
        this.out.writeUTF(type.toString());
      }

      // Start save thread
      timer.schedule(new TimerTask() {
        @Override public void run() { save(); }
      }, SAVE_DELAY, SAVE_DELAY);
    } else {
      this.out = null;
    }

    // activate profiler
    profileStartTime = execStartTimeNanos;
  }