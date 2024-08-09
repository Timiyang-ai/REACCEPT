@Override
    public Set<String> updateConfigsForCluster(
            @NotBlank(message = "No cluster id entered. Unable to update configurations.")
            final String id,
            @NotEmpty(message = "No configs entered. Unable to update.")
            final Set<String> configs
    ) throws GenieException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("called with id " + id + " and configs " + configs);
        }
        final Cluster cluster = this.clusterRepo.findOne(id);
        if (cluster != null) {
            cluster.setConfigs(configs);
            return cluster.getConfigs();
        } else {
            throw new GenieNotFoundException("No cluster with id " + id + " exists.");
        }
    }