@Override
    public void createJob(
        @NotNull final JobRequest jobRequest,
        @NotNull final JobMetadata jobMetadata,
        @NotNull final Job job,
        @NotNull final JobExecution jobExecution
    ) throws GenieException {
        log.debug(
            "Called with\nRequest:\n{}\nMetadata:\n{}\nJob:\n{}\nExecution:\n{}\n",
            jobRequest,
            jobMetadata,
            job,
            jobExecution
        );

        final String jobId = jobRequest.getId().orElseThrow(() -> new GeniePreconditionException("No job id entered"));
        final JobEntity jobEntity = this.toEntity(jobId, jobRequest, jobMetadata, job, jobExecution);
        try {
            this.jobRepository.save(jobEntity);
        } catch (final DataIntegrityViolationException e) {
            throw new GenieConflictException("A job with id " + jobId + " already exists", e);
        }
    }