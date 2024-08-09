@Override
    @Transactional(
            rollbackFor = {
                    GenieException.class,
                    ConstraintViolationException.class
            }
    )
    public void setApplicationInfoForJob(
            @NotBlank(message = "No job id entered. Unable to update app info for job.")
            final String id,
            @NotBlank(message = "No app id entered. Unable to update app info for job.")
            final String appId,
            @NotBlank(message = "No app name entered. unable to update app info for job.")
            final String appName
    ) throws GenieException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Setting the application info for job with id " + id);
        }
        final JobEntity jobEntity = this.findJob(id);
        jobEntity.setApplicationId(appId);
        jobEntity.setApplicationName(appName);
    }