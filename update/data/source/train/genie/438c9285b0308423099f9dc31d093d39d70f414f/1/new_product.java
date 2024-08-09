@Override
    public String coordinateJob(
        @NotNull(message = "No jobRequest provided. Unable to submit job for execution.")
        @Valid
        final JobRequest jobRequest,
        final String clientHost
    ) throws GenieException {
        log.debug("Called with job request {}", jobRequest);

        // Log the request as soon as it comes in. This method returns a job request DTO with an id in it as the
        // orginal request may or may not have an id.
        final JobRequest jobRequestWithId =
            this.jobPersistenceService.createJobRequest(jobRequest);

        if (StringUtils.isNotBlank(clientHost)) {
            this.jobPersistenceService.addClientHostToJobRequest(jobRequestWithId.getId(), clientHost);
        }

        String jobArchivalLocation = null;
        if (!jobRequestWithId.isDisableLogArchival()) {
            if(baseArchiveLocation == null) {
                throw new
                    GeniePreconditionException("Job archival is enabled but base location for archival is null.");
            }
            jobArchivalLocation = baseArchiveLocation + "/" + jobRequestWithId.getId();
        }
        // create the job object in the database with status INIT
        final Job job  = new Job.Builder(
                jobRequest.getName(),
                jobRequest.getUser(),
                jobRequest.getVersion()
            )
            .withArchiveLocation(jobArchivalLocation)
            .withDescription(jobRequest.getDescription())
            .withId(jobRequestWithId.getId())
            .withStatus(JobStatus.INIT)
            .withStatusMsg("Job Accepted and in initialization phase.")
            .build();

        this.jobPersistenceService.createJob(job);
        this.jobSubmitterService.submitJob(jobRequestWithId);
        return jobRequestWithId.getId();
    }