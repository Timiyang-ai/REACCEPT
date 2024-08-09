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
        if (LOG.isDebugEnabled()) {
            LOG.debug("Setting the command info for job with id " + id);
        }
        final JobEntity jobEntity = this.findJob(id);
        //TODO: Should we check if this is valid
        jobEntity.setCommandId(commandId);
        jobEntity.setCommandName(commandName);
    }