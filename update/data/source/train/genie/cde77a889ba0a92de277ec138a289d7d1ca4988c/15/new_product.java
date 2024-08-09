@Override
    @Transactional(
            rollbackFor = {
                    GenieException.class,
                    ConstraintViolationException.class
            }
    )
    public void setClusterInfoForJob(
            @NotBlank(message = "No job id entered. Unable to update cluster info for job.")
            final String id,
            @NotBlank(message = "No cluster id entered. Unable to update cluster info for job.")
            final String clusterId,
            @NotBlank(message = "No cluster name entered. Unable to update cluster info for job.")
            final String clusterName
    ) throws GenieException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Setting the application info for job with id " + id);
        }
        final JobEntity jobEntity = this.findJob(id);
        jobEntity.setExecutionClusterId(clusterId);
        jobEntity.setExecutionClusterName(clusterName);
    }