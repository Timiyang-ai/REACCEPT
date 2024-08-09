public void init() {
    status = new AtomicInteger();

    jobListeners = new ArrayList<JobListener>();
    jobEntryListeners = new ArrayList<JobEntryListener>();
    delegationListeners = new ArrayList<DelegationListener>();

    activeJobEntryTransformations = new HashMap<JobEntryCopy, JobEntryTrans>();
    activeJobEntryJobs = new HashMap<JobEntryCopy, JobEntryJob>();

    extensionDataMap = new HashMap<String, Object>();

    jobTracker = new JobTracker( jobMeta );
    synchronized ( jobEntryResults ) {
      jobEntryResults.clear();
    }

    errors = new AtomicInteger( 0 );
    batchId = -1;
    passedBatchId = -1;
    maxJobEntriesLogged = Const.toInt( EnvUtil.getSystemProperty( Const.KETTLE_MAX_JOB_ENTRIES_LOGGED ), 1000 );

    result = null;
    startJobEntryCopy = null;
    startJobEntryResult = null;

    this.setDefaultLogCommitSize();
  }