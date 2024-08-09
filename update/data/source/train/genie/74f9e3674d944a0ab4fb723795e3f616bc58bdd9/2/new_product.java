@Override
    public void createJobExecution(
        @NotNull(message = "Job Request is null so cannot be saved")
        final JobExecution jobExecution
    ) throws GenieException {
        log.debug("Called with jobExecution: {}", jobExecution);

        // Since a job request object should always exist before the job object is saved
        // the id should never be null
        if (StringUtils.isBlank(jobExecution.getId())) {
            throw new GeniePreconditionException("Cannot create a job execution entry with id blank or null");
        }

        this.updateJobStatus(jobExecution.getId(), JobStatus.RUNNING, "Job is Running.");
        final JobEntity jobEntity = jobRepo.findOne(jobExecution.getId());
        if (jobEntity == null) {
            throw new GenieNotFoundException("Cannot find the job for the id of the jobExecution specified.");
        }

        final JobExecutionEntity jobExecutionEntity = new JobExecutionEntity();

        jobExecutionEntity.setId(jobExecution.getId());
        jobExecutionEntity.setHostName(jobExecution.getHostName());
        jobExecutionEntity.setProcessId(jobExecution.getProcessId());
        jobExecutionEntity.setCheckDelay(jobExecution.getCheckDelay());
        jobExecutionEntity.setTimeout(jobExecution.getTimeout());

        jobEntity.setExecution(jobExecutionEntity);
    }