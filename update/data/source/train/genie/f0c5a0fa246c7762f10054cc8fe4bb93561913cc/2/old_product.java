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

        // Validation successful. init state in DB - return if job already exists
        try {
            final Job persistedJob = this.jobRepo.save(job);
            // if job can be launched, update the URIs
            final String hostName = this.netUtil.getHostName();
            persistedJob.setHostName(hostName);
            persistedJob.setOutputURI(
                    getEndPoint(hostName)
                            + "/" + this.jobDirPrefix
                            + "/" + persistedJob.getId()
            );
            persistedJob.setKillURI(
                    getEndPoint(hostName)
                            + "/" + this.jobResourcePrefix
                            + "/" + persistedJob.getId()
            );

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