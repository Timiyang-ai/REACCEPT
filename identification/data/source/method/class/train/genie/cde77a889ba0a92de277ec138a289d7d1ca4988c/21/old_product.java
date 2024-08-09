@Override
    @Transactional(
            rollbackFor = {
                    GenieException.class,
                    ConstraintViolationException.class
            }
    )
    public void setJobStatus(
            @NotBlank(message = "No id entered for the job. Unable to update the status.")
            final String id,
            @NotNull(message = "No status entered unable to update.")
            final JobStatus status,
            final String msg
    ) throws GenieException {
        LOG.debug("Setting job with id " + id + " to status " + status + " for reason " + msg);
        final Job job = this.jobRepo.findOne(id);
        if (job != null) {
            job.setJobStatus(status, msg);
        } else {
            throw new GenieNotFoundException("No job with id " + id + " exists");
        }
    }