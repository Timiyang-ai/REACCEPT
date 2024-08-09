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
        if (this.jobRequestRepo.exists(jobId)) {
            throw new GenieConflictException("A job with id " + jobId + " already exists");
        }

        final JobRequestEntity jobRequestEntity = this.jobRequestDtoToEntity(jobId, jobRequest);
        final JobMetadataEntity metadataEntity = this.jobMetadataDtoToEntity(jobMetadata);
        final JobEntity jobEntity = this.jobDtoToEntity(job);
        final JobExecutionEntity jobExecutionEntity = this.jobExecutionDtoToEntity(jobExecution);

        this.jobRequestRepo.save(jobRequestEntity);

        jobEntity.setRequest(jobRequestEntity);
        this.jobRepo.save(jobEntity);
        metadataEntity.setRequest(jobRequestEntity);
        this.jobMetadataRepository.save(metadataEntity);
        jobExecutionEntity.setJob(jobEntity);
        this.jobExecutionRepo.save(jobExecutionEntity);
    }