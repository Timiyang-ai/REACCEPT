public void init() {
    jobListeners = new ArrayList<JobListener>();
    jobEntryListeners = new ArrayList<JobEntryListener>();
    delegationListeners = new ArrayList<DelegationListener>();

    activeJobEntryTransformations = new HashMap<JobEntryCopy, JobEntryTrans>();
    activeJobEntryJobs = new HashMap<JobEntryCopy, JobEntryJob>();

    active = new AtomicBoolean(false);
    stopped = new AtomicBoolean(false);
    jobTracker = new JobTracker(jobMeta);
    synchronized (jobEntryResults) {
      jobEntryResults.clear(); 
    }
    initialized = new AtomicBoolean(false);
    finished = new AtomicBoolean(false);
    errors = new AtomicInteger(0);
    batchId = -1;
    passedBatchId = -1;
    maxJobEntriesLogged = Const.toInt(EnvUtil.getSystemProperty(Const.KETTLE_MAX_JOB_ENTRIES_LOGGED), 1000);

    result = null;
    this.setDefaultLogCommitSize();
  }