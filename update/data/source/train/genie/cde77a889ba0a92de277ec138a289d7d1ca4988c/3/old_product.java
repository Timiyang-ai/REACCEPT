@Override
    @Transactional(
            rollbackFor = {
                    GenieException.class,
                    ConstraintViolationException.class
            }
    )
    @Retryable(JpaOptimisticLockingFailureException.class)
    public long setUpdateTime(
            @NotBlank(message = "No job id entered. Unable to set update time.")
            final String id
    ) throws GenieException {
        LOG.debug("Updating db for job: " + id);
        final Job job = this.jobRepo.findOne(id);
        if (job == null) {
            throw new GenieNotFoundException(
                    "No job with id " + id + " exists"
            );
        }

        final long lastUpdatedTimeMS = System.currentTimeMillis();
        job.setJobStatus(JobStatus.RUNNING, "Job is running");
        job.setUpdated(new Date(lastUpdatedTimeMS));
        return lastUpdatedTimeMS;
    }