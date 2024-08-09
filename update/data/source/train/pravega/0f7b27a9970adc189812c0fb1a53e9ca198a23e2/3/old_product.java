public void initialize(DistributedLogNamespace namespace) throws DurableDataLogException {
        Preconditions.checkNotNull(namespace, "namespace");
        Preconditions.checkState(this.logManager == null, "LogHandle is already initialized.");

        // Initialize Log Manager and Log Writer.
        boolean success = false;
        try {
            this.logManager = namespace.openLog(logName);
            log.debug("{}: Opened LogManager.", this.logName);

            this.logWriter = this.logManager.startAsyncLogSegmentNonPartitioned();
            log.debug("{}: Opened LogWriter.", this.logName);
            success = true;
        } catch (OwnershipAcquireFailedException ex) {
            // This means one of two things:
            // 1. Someone else currently holds the exclusive write lock.
            // 2. Someone else held the exclusive write lock, crashed, and ZooKeeper did not figure it out yet (due to a long Session Timeout).
            throw new DataLogWriterNotPrimaryException(String.format("Unable to acquire exclusive Writer for log '%s'.", logName), ex);
        } catch (IOException ex) {
            // Log does not exist or some other issue happened. Note that LogNotFoundException inherits from IOException, so it's also handled here.
            throw new DataLogNotAvailableException(String.format("Unable to create DistributedLogManager for log '%s'.", logName), ex);
        } catch (Exception ex) {
            // General exception, configuration issue, etc.
            throw new DataLogInitializationException(String.format("Unable to create DistributedLogManager for log '%s'.", logName), ex);
        } finally {
            if (!success) {
                try {
                    close();
                } catch (Exception ex) {
                    log.error("Unable to cleanup resources after the failed attempt to create a LogHandle for '{}'. {}", logName, ex);
                }
            }
        }

        try {
            this.lastTransactionId.set(this.logManager.getLastTxId());
        } catch (LogEmptyException ex) {
            this.lastTransactionId.set(0);
        } catch (Exception ex) {
            throw new DataLogInitializationException(String.format("Unable to determine last transaction Id for log '%s'.", logName), ex);
        }

        log.info("{}: Initialized (LastTransactionId = {}).", this.logName, this.lastTransactionId);
    }