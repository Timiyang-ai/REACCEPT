public String coordinateJob(
        @NotNull(message = "No jobRequest provided. Unable to submit job for execution.")
        @Valid
        final JobRequest jobRequest,
        final String clientHost
    ) throws GenieException {
        log.debug("Called with job request {}", jobRequest);
        if (StringUtils.isBlank(jobRequest.getId())) {
            throw new GenieServerException("Id of the jobRequest cannot be null");
        }

        // Log the job request and optionally the client host
        this.jobPersistenceService.createJobRequest(jobRequest, clientHost);

        String archiveLocation = null;
        if (!jobRequest.isDisableLogArchival()) {
            archiveLocation = this.baseArchiveLocation
                + JobConstants.FILE_PATH_DELIMITER
                + jobRequest.getId()
                + ".tar.gz";
        }

        // create the job object in the database with status INIT
        final Job.Builder jobBuilder = new Job.Builder(
            jobRequest.getName(),
            jobRequest.getUser(),
            jobRequest.getVersion(),
            jobRequest.getCommandArgs()
        )
            .withArchiveLocation(archiveLocation)
            .withDescription(jobRequest.getDescription())
            .withId(jobRequest.getId())
            .withTags(jobRequest.getTags());

        if (this.canRunJob()) {
            jobBuilder
                .withStatus(JobStatus.INIT)
                .withStatusMsg("Job Accepted and in initialization phase.");
            // TODO: if this throws exception the job will never be marked failed
            this.jobPersistenceService.createJob(jobBuilder.build());
            try {
                final Future<?> task
                    = this.taskExecutor.submit(new JobLauncher(this.jobSubmitterService, jobRequest, this.registry));

                // Tell the system a new job has been scheduled so any actions can be taken
                this.eventPublisher.publishEvent(new JobScheduledEvent(jobRequest.getId(), task, this));
            } catch (final TaskRejectedException e) {
                final String errorMsg = "Unable to launch job due to exception: " + e.getMessage();
                this.jobPersistenceService.updateJobStatus(jobRequest.getId(), JobStatus.FAILED, errorMsg);
                throw new GenieServerException(errorMsg, e);
            }
            return jobRequest.getId();
        } else {
            jobBuilder
                .withStatus(JobStatus.FAILED)
                .withStatusMsg("Unable to run job due to host being too busy during request.");
            this.jobPersistenceService.createJob(jobBuilder.build());
            throw new GenieServerUnavailableException("Reached max running jobs on this host. Unable to run job.");
        }
    }