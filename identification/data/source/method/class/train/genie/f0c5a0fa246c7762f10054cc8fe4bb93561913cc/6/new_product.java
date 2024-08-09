@Override
    @Transactional
    public Job createJob(
            @NotNull(message = "No job entered. Unable to create.")
            @Valid
            final Job job
    ) throws GenieException {
        if (StringUtils.isNotEmpty(job.getId()) && this.jobRepo.exists(job.getId())) {
            throw new GenieConflictException("A job with id " + job.getId() + " already exists. Unable to save.");
        }
        // validate parameters
        job.setJobStatus(JobStatus.INIT, "Initializing job");

        if (StringUtils.isEmpty(job.getId())) {
            job.setId(UUID.randomUUID().toString());
        }

        // Validation successful. init state in DB - return if job already exists
        try {
            // if job can be launched, update the URIs
            final String hostName = this.netUtil.getHostName();
            job.setHostName(hostName);
            final String endpoint = getEndPoint(hostName);
            job.setOutputURI(endpoint + "/" + this.jobDirPrefix + "/" + job.getId());
            job.setKillURI(endpoint + "/" + this.jobResourcePrefix + "/" + job.getId());
            final Job persistedJob = this.jobRepo.save(job);

            // increment number of submitted jobs as we have successfully
            // persisted it in the database.
            this.stats.incrGenieJobSubmissions();
            return persistedJob;
        } catch (final RuntimeException e) {
            //This will catch runtime as well
            LOG.error("Can't create entity in the database", e);
            throw new GenieServerException(e);
        }
    }