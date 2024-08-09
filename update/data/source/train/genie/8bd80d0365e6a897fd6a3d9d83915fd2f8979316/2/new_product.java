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
            if (this.jobSearchService.getAllRunningJobExecutionsOnHost(this.hostname).size() > this.maxRunningJobs) {
                throw new GenieServerUnavailableException("Reached max running jobs on this host. Rejecting request");
            }

            final File jobWorkingDir;

            try {
                jobWorkingDir = new File(baseWorkingDirPath.getFile(), "/" + id);
            } catch (final IOException ioe) {
                throw new GenieServerException("Could not resolve job working directory due to exception", ioe);
            }

            // Resolve the cluster for the job request based on the tags specified
            //TODO: Combine the cluster and command selection into a single method/database query for efficiency
            final Cluster cluster
                = this.clusterLoadBalancer.selectCluster(this.clusterService.chooseClusterForJobRequest(jobRequest));

            // Resolve the command for the job request based on command tags and cluster chosen
            final Set<CommandStatus> enumStatuses = EnumSet.noneOf(CommandStatus.class);
            enumStatuses.add(CommandStatus.ACTIVE);
            Command command = null;

            // TODO: what happens if the get method throws an error we don't mark the job failed here
            for (final Command cmd : this.clusterService.getCommandsForCluster(cluster.getId(), enumStatuses)) {
                if (cmd.getTags().containsAll(jobRequest.getCommandCriteria())) {
                    command = cmd;
                    break;
                }
            }

            if (command == null) {
                throw new GeniePreconditionException(
                    "No command found matching all command criteria on cluster. Unable to continue."
                );
            }

            // TODO: What do we do about application status? Should probably check here
            final List<Application> applications = new ArrayList<>();
            if (jobRequest.getApplications().isEmpty()) {
                applications.addAll(this.commandService.getApplicationsForCommand(command.getId()));
            } else {
                for (final String applicationId : jobRequest.getApplications()) {
                    applications.add(this.applicationService.getApplication(applicationId));
                }
            }

            // Job can be run as there is a valid set of cluster, command and applications
            // Save all the runtime environment information for the job
            this.jobPersistenceService.updateJobWithRuntimeEnvironment(
                id,
                cluster.getId(),
                command.getId(),
                applications.stream().map(Application::getId).collect(Collectors.toList())
            );

            // construct the job execution environment object for this job request
            final JobExecutionEnvironment jee = new JobExecutionEnvironment.Builder(
                jobRequest,
                cluster,
                command,
                jobWorkingDir
            )
                .withApplications(applications)
                .build();

            // The map object stores the context for all the workflow tasks
            final Map<String, Object> context = new HashMap<>();

            context.put(JobConstants.JOB_EXECUTION_ENV_KEY, jee);
            context.put(JobConstants.FILE_TRANSFER_SERVICE_KEY, this.fileTransferService);

            final String runScript;
            try {
                // Create the job working directory
                Utils.createDirectory(jobWorkingDir.getCanonicalPath());

                // Run script path for this job like basedir/jobId/run.sh
                runScript = jobWorkingDir.getCanonicalPath()
                    + JobConstants.FILE_PATH_DELIMITER
                    + JobConstants.GENIE_JOB_LAUNCHER_SCRIPT;

            } catch (final IOException e) {
                throw new GenieServerException("Job submission failed.", e);
            }

            try (final Writer writer = new OutputStreamWriter(new FileOutputStream(runScript), "UTF-8")) {
                context.put(JobConstants.WRITER_KEY, writer);

                for (WorkflowTask workflowTask : this.jobWorkflowTasks) {
                    workflowTask.executeTask(context);
                }
            } catch (final IOException ioe) {
                throw new GenieServerException("Failed to execute job", ioe);
            }

            final JobExecution jobExecution = (JobExecution) context.get(JobConstants.JOB_EXECUTION_DTO_KEY);

            // Job Execution will be null in local mode.
            if (jobExecution != null) {
                // Persist the jobExecution information. This also updates jobStatus to Running
                this.jobPersistenceService.createJobExecution(jobExecution);

                // Publish a job start Event
                this.applicationEventPublisher.publishEvent(new JobStartedEvent(jobExecution, this));
            }
        } catch (final Exception e) {
            log.error(e.getLocalizedMessage(), e);
            this.jobPersistenceService.updateJobStatus(id, JobStatus.INVALID, e.getLocalizedMessage());
            throw e;
        }
    }