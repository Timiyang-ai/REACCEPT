@Override
    @Transactional(
            rollbackFor = {
                    GenieException.class,
                    ConstraintViolationException.class
            }
    )
    public void setProcessIdForJob(
            @NotBlank(message = "No job id entered. Unable to set process id")
            final String id,
            final int pid
    ) throws GenieException {
        LOG.debug("Setting the id of process for job with id " + id + " to " + pid);
        final Job job = this.jobRepo.findOne(id);
        if (job != null) {
            job.setProcessHandle(pid);
        } else {
            throw new GenieNotFoundException("No job with id " + id + " exists");
        }
    }