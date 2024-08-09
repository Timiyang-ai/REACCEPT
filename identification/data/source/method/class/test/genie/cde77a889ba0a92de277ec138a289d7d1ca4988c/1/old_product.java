@Override
    @Transactional(readOnly = true)
    public Set<String> getConfigsForCluster(
            @NotBlank(message = "No cluster id sent. Cannot retrieve configurations.")
            final String id
    ) throws GenieException {
        LOG.debug("called");
        final Cluster cluster = this.clusterRepo.findOne(id);
        if (cluster != null) {
            return cluster.getConfigs();
        } else {
            throw new GenieNotFoundException("No cluster with id " + id + " exists.");
        }
    }