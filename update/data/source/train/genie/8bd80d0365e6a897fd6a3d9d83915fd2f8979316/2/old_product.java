@Override
    public void submitJob(
        @NotNull(message = "No job provided. Unable to submit job for execution.")
        @Valid
        final JobRequest jobRequest
    ) throws GenieException {
        log.debug("called with job request {}", jobRequest);

        if (this.jobSearchService.getAllRunningJobExecutionsOnHost(this.hostname).size() > this.maxRunningJobs) {
            throw new GenieServerUnavailableException("Reached max running jobs on this host. Rejecting request");
        }

        final File jobWorkingDir;

        try {
            jobWorkingDir = new File(baseWorkingDirPath.getFile(), "/" + jobRequest.getId());
        } catch (IOException ioe) {
            throw new GenieServerException("Could not resolve job working directory due to exception" + ioe);
        }

        // Resolve the cluster for the job request based on the tags specified
        final Cluster cluster;
        try {
            cluster = clusterLoadBalancer
                .selectCluster(clusterService.chooseClusterForJobRequest(jobRequest));
        } catch (GeniePreconditionException gpe) {
            this.jobPersistenceService.updateJobStatus(
                jobRequest.getId(),
                JobStatus.INVALID,
                "Unable to resolve to valid cluster/command combination for criteria specified.");
            throw gpe;
        }

        // Resolve the command for the job request based on command tags and cluster choosen
        final Set<CommandStatus> enumStatuses = EnumSet.noneOf(CommandStatus.class);
        enumStatuses.add(CommandStatus.ACTIVE);
        Command command = null;

        for (final Command cmd : this.clusterService.getCommandsForCluster(
            cluster.getId(),
            enumStatuses
        )) {
            if (cmd.getTags().containsAll(jobRequest.getCommandCriteria())) {
                command = cmd;
                break;
            }
        }

        if (command == null) {
            final String msg = "No command found for params. Unable to continue.";
            log.error(msg);
            throw new GeniePreconditionException(msg);
        }

        // Job can be run as there is a valid cluster/command combination for it.
        // Update cluster and command information for the job
        this.jobPersistenceService.updateClusterForJob(
            jobRequest.getId(),
            cluster.getId());

        this.jobPersistenceService.updateCommandForJob(
            jobRequest.getId(),
            command.getId());

        // construct the job execution environment object for this job request
        final JobExecutionEnvironment jee = new JobExecutionEnvironment.Builder(
            jobRequest,
            cluster,
            command,
            jobWorkingDir
        )
            .withApplications(commandService.getApplicationsForCommand(command.getId()))
            .build();

        // The map object stores the context for all the workflow tasks
        final Map<String, Object> context = new HashMap<>();

        context.put(JobConstants.JOB_EXECUTION_ENV_KEY, jee);
        context.put(JobConstants.FILE_TRANSFER_SERVICE_KEY, fileTransferService);

        final String runScript;
        try {
            // Create the job working directory
            Utils.createDirectory(jobWorkingDir.getCanonicalPath());

            // Run script path for this job like basedir/jobuuid/run.sh
            runScript = jobWorkingDir.getCanonicalPath()
                + JobConstants.FILE_PATH_DELIMITER
                + JobConstants.GENIE_JOB_LAUNCHER_SCRIPT;

        } catch (IOException e) {
            throw new GenieServerException("Job submission failed.", e);
        }

        try (final Writer writer = new OutputStreamWriter(new FileOutputStream(runScript), "UTF-8")) {
            context.put(JobConstants.WRITER_KEY, writer);

            for (WorkflowTask workflowTask : this.jobWorkflowTasks) {
                workflowTask.executeTask(context);
            }
        } catch (IOException ioe) {
            throw new GenieServerException("Failed to execute job");
        }

        final JobExecution jobExecution = (JobExecution) context.get(JobConstants.JOB_EXECUTION_DTO_KEY);

        // Job Execution will be null in local mode.
        if (jobExecution != null) {
            // Persist the jobExecution information. This also updates jobStatus to Running
            this.jobPersistenceService.createJobExecution(jobExecution);

            // Publish a job start Event
            this.applicationEventPublisher.publishEvent(new JobStartedEvent(jobExecution, this));
        }
    }