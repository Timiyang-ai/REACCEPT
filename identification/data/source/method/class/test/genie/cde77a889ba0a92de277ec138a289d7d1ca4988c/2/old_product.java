@Override
    @Transactional(
            rollbackFor = {
                    GenieException.class,
                    ConstraintViolationException.class
            }
    )
    public void setCommandInfoForJob(
            @NotBlank(message = "No job id entered. Unable to set command info for job.")
            final String id,
            @NotBlank(message = "No command id entered. Unable to set command info for job.")
            final String commandId,
            @NotBlank(message = "No command name entered. Unable to set command info for job.")
            final String commandName
    ) throws GenieException {
        LOG.debug("Setting the command info for job with id " + id);
        final Job job = this.jobRepo.findOne(id);
        if (job != null) {
            //TODO: Should we check if this is valid
            job.setCommandId(commandId);
            job.setCommandName(commandName);
        } else {
            throw new GenieNotFoundException("No job with id " + id + " exists");
        }
    }