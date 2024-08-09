@SuppressFBWarnings(
        value = "REC_CATCH_EXCEPTION",
        justification = "We catch exception to make sure we always mark job failed."
    )
    @Override
    public void submitJob(
        @Valid
        @NotNull(message = "No job provided. Unable to submit job for execution.")
        final JobRequest jobRequest,
        @Valid
        @NotNull(message = "No cluster provided. Unable to submit job for execution")
        final Cluster cluster,
        @Valid
        @NotNull(message = "No command provided. Unable to submit job for execution")
        final Command command,
        @NotNull(message = "No applications provided. Unable to submit job for execution")
        final List<Application> applications,
        @Min(value = 1, message = "Memory can't be less than 1 MB")
        final int memory
    ) throws GenieException {
        final long start = System.nanoTime();

        try {
            log.info("Beginning local job submission for {}", jobRequest);
            final String id = jobRequest.getId().orElseThrow(() -> new GenieServerException("No job id found."));

            try {
                final File jobWorkingDir = this.createJobWorkingDirectory(id);
                final File runScript = this.createRunScript(jobWorkingDir);

                // The map object stores the context for all the workflow tasks
                final Map<String, Object> context
                    = this.createJobContext(jobRequest, cluster, command, applications, memory, jobWorkingDir);

                // Execute the job
                final JobExecution jobExecution = this.executeJob(context, runScript);

                // Job Execution will be null in local mode.
                if (jobExecution != null) {
                    // Persist the jobExecution information. This also updates jobStatus to Running
                    final long createJobExecutionStart = System.nanoTime();
                    try {
                        log.info("Saving job execution for job {}", jobRequest.getId());
                        this.jobPersistenceService.setJobRunningInformation(
                            id,
                            jobExecution.
                                getProcessId()
                                .orElseThrow(() ->
                                    new GenieServerException("No process id returned. Unable to persist")
                                ),
                            jobExecution.getCheckDelay().orElse(Command.DEFAULT_CHECK_DELAY),
                            jobExecution
                                .getTimeout()
                                .orElseThrow(() ->
                                    new GenieServerException("No timeout date returned. Unable to persist")
                                )
                        );
                    } finally {
                        this.saveJobExecutionTimer
                            .record(System.nanoTime() - createJobExecutionStart, TimeUnit.NANOSECONDS);
                    }

                    // Publish a job start Event
                    final long publishEventStart = System.nanoTime();
                    try {
                        log.info("Publishing job started event for job {}", id);
                        this.eventPublisher.publishEvent(new JobStartedEvent(jobExecution, this));
                    } finally {
                        this.publishJobStartedEventTimer
                            .record(System.nanoTime() - publishEventStart, TimeUnit.NANOSECONDS);
                    }
                }
            } catch (final GeniePreconditionException gpe) {
                log.error(gpe.getMessage(), gpe);
                this.eventMulticaster.multicastEvent(
                    new JobFinishedEvent(id, JobFinishedReason.INVALID, gpe.getMessage(), this)
                );
                throw gpe;
            } catch (final Exception e) {
                log.error(e.getMessage(), e);
                this.eventMulticaster.multicastEvent(
                    new JobFinishedEvent(id, JobFinishedReason.FAILED_TO_INIT, e.getMessage(), this)
                );
                throw e;
            }
        } finally {
            this.overallSubmitTimer.record(System.nanoTime() - start, TimeUnit.NANOSECONDS);
        }
    }