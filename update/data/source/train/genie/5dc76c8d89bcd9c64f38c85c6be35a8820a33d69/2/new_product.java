@Override
    public String createCluster(
        @NotNull(message = "No cluster entered. Unable to create.")
        @Valid
        final Cluster cluster
    ) throws GenieException {
        log.debug("Called to create cluster {}", cluster);
        final Optional<String> clusterId = cluster.getId();
        if (clusterId.isPresent() && this.clusterRepo.exists(clusterId.get())) {
            throw new GenieConflictException("A cluster with id " + clusterId.get() + " already exists");
        }

        final ClusterEntity clusterEntity = new ClusterEntity();
        clusterEntity.setId(cluster.getId().orElse(UUID.randomUUID().toString()));
        this.updateAndSaveClusterEntity(clusterEntity, cluster);
        return clusterEntity.getId();
    }