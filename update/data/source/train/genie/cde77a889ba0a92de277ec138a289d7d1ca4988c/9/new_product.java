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
        if (LOG.isDebugEnabled()) {
            LOG.debug("Setting the id of process for job with id " + id + " to " + pid);
        }
        this.findJob(id).setProcessHandle(pid);
    }