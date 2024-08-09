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

        // Log the job request
        this.jobPersistenceService.createJobRequest(jobRequest);

        if (StringUtils.isNotBlank(clientHost)) {
            this.jobPersistenceService.addClientHostToJobRequest(jobRequest.getId(), clientHost);
        }

        String archiveLocation = null;
        if (!jobRequest.isDisableLogArchival()) {
            archiveLocation = this.baseArchiveLocation
                + JobConstants.FILE_PATH_DELIMITER
                + jobRequest.getId()
                + ".tar.gz";
        }

        // create the job object in the database with status INIT
        final Job job = new Job.Builder(
            jobRequest.getName(),
            jobRequest.getUser(),
            jobRequest.getVersion(),
            jobRequest.getCommandArgs()
        )
            .withArchiveLocation(archiveLocation)
            .withDescription(jobRequest.getDescription())
            .withId(jobRequest.getId())
            .withStatus(JobStatus.INIT)
            .withTags(jobRequest.getTags())
            .withStatusMsg("Job Accepted and in initialization phase.")
            .build();

        this.jobPersistenceService.createJob(job);
        this.jobSubmitterService.submitJob(jobRequest);
        return jobRequest.getId();
    }