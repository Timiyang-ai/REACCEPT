@Override
    public void updateJobStatus(
        @NotBlank(message = "No job id entered. Unable to update.")
        final String id,
        @NotNull(message = "Status cannot be null.")
        final JobStatus jobStatus,
        @NotBlank(message = "Status message cannot be empty.")
        final String statusMsg
    ) throws GenieException {

        log.debug("Called to update job with id {}, status {} and statusMsg \"{}\"", id, jobStatus, statusMsg);

        final JobEntity jobEntity = this.jobRepo.findOne(id);
        if (jobEntity == null) {
            throw new GenieNotFoundException("No job exists for the id specified");
        }

        jobEntity.setStatus(jobStatus);
        jobEntity.setStatusMsg(statusMsg);

        if (jobStatus.equals(JobStatus.RUNNING)) {
            // Status being changed to running so set start date.
            jobEntity.setStarted(new Date());
        } else if (jobEntity.getStarted() != null && (jobStatus.equals(JobStatus.KILLED)
            || jobStatus.equals(JobStatus.FAILED)
            || jobStatus.equals(JobStatus.SUCCEEDED))) {

            // Since start date is set the job was running previously and now has finished
            // with status killed, failed or succeeded. So we set the job finish time.
            jobEntity.setFinished(new Date());
        }
        this.jobRepo.save(jobEntity);
    }