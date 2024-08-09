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

        // If the status is either failed, killed or succeeded then set the finish time of the job as well
        if (jobStatus.equals(JobStatus.KILLED)
            || jobStatus.equals(JobStatus.FAILED)
            || jobStatus.equals(JobStatus.SUCCEEDED)) {

            jobEntity.setFinished(new Date());
        }
        this.jobRepo.save(jobEntity);
    }