@Override
    public Cluster createCluster(
            @NotNull(message = "No cluster entered. Unable to create.")
            @Valid
            final Cluster cluster
    ) throws GenieException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Called to create cluster " + cluster.toString());
        }
        if (StringUtils.isEmpty(cluster.getId())) {
            cluster.setId(UUID.randomUUID().toString());
        }
        if (this.clusterRepo.exists(cluster.getId())) {
            throw new GenieConflictException("A cluster with id " + cluster.getId() + " already exists");
        }

        return this.clusterRepo.save(cluster);
    }