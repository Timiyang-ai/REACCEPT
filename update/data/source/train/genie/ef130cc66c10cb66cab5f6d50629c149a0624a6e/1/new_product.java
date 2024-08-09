@SuppressFBWarnings(
        value = "REC_CATCH_EXCEPTION",
        justification = "We catch exception to make sure we always mark job failed."
    )
    @Override
    public void submitJob(
        @NotNull(message = "No job provided. Unable to submit job for execution.")
        @Valid
        final JobRequest jobRequest
    ) throws GenieException {
        log.debug("called with job request {}", jobRequest);
        final String id = jobRequest.getId();

        try {
            final File jobWorkingDir = this.createJobWorkingDirectory(id);
            final File runScript = this.createRunScript(jobWorkingDir);

            //TODO: Combine the cluster and command selection into a single method/database query for efficiency
            // Resolve the cluster for the job request based on the tags specified
            final Cluster cluster = this.getCluster(jobRequest);
            // Resolve the command for the job request based on command tags and cluster chosen
            final Command command = this.getCommand(jobRequest, cluster);
            // Resolve the applications to use based on the command that was selected
            final List<Application> applications = this.getApplications(jobRequest, command);

            // Job can be run as there is a valid set of cluster, command and applications
            // Save all the runtime environment information for the job
            this.jobPersistenceService.updateJobWithRuntimeEnvironment(
                id,
                cluster.getId(),
                command.getId(),
                applications.stream().map(Application::getId).collect(Collectors.toList())
            );

            // The map object stores the context for all the workflow tasks
            final Map<String, Object> context
                = this.createJobContext(jobRequest, cluster, command, applications, jobWorkingDir);

            // Execute the job
            final JobExecution jobExecution = this.executeJob(context, runScript);

            // Job Execution will be null in local mode.
            if (jobExecution != null) {
                // Persist the jobExecution information. This also updates jobStatus to Running
                this.jobPersistenceService.createJobExecution(jobExecution);

                // Publish a job start Event
                this.applicationEventPublisher.publishEvent(new JobStartedEvent(jobExecution, this));
            }
        } catch (final GeniePreconditionException gpe) {
            log.error(gpe.getMessage(), gpe);
            this.jobPersistenceService.updateJobStatus(id, JobStatus.INVALID, gpe.getMessage());
            throw gpe;
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            this.jobPersistenceService.updateJobStatus(id, JobStatus.FAILED, e.getMessage());
            throw e;
        }
    }