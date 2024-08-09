@Override
    public void createJob(
        @NotNull(message = "No Job provided to create")
        final Job job
    ) throws GenieException {
        log.debug("Called with job: {}", job);

        // Since a job request object should always exist before the job object is saved
        // the id should never be null
        if (StringUtils.isBlank(job.getId())) {
            throw new GeniePreconditionException("Cannot create a job without the id specified");
        }

        // check if job already exists in the database
        if (this.jobRepo.exists(job.getId())) {
            throw new GenieConflictException("A job with id " + job.getId() + " already exists");
        }

        final JobRequestEntity jobRequestEntity = this.jobRequestRepo.findOne(job.getId());

        if (jobRequestEntity == null) {
            throw new GeniePreconditionException("Cannot find the job request for the id of the job specified.");
        }

        final JobEntity jobEntity = new JobEntity();

        jobEntity.setId(job.getId());
        jobEntity.setName(job.getName());
        jobEntity.setUser(job.getUser());
        jobEntity.setVersion(job.getVersion());
        jobEntity.setArchiveLocation(job.getArchiveLocation());
        jobEntity.setDescription(job.getDescription());

        if (job.getStarted() != null) {
            jobEntity.setStarted(job.getStarted());
        }
        jobEntity.setStatus(job.getStatus());
        jobEntity.setStatusMsg(job.getStatusMsg());
        jobEntity.setTags(job.getTags());
        jobEntity.setCommandArgs(job.getCommandArgs());

        jobRequestEntity.setJob(jobEntity);
    }