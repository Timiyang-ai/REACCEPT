@Override
    public String createCluster(
        @NotNull(message = "No cluster entered. Unable to create.")
        @Valid
        final Cluster cluster
    ) throws GenieException {
        log.debug("Called to create cluster {}", cluster.toString());
        if (StringUtils.isNotBlank(cluster.getId()) && this.clusterRepo.exists(cluster.getId())) {
            throw new GenieConflictException("A cluster with id " + cluster.getId() + " already exists");
        }

        final ClusterEntity clusterEntity = new ClusterEntity();
        clusterEntity.setId(StringUtils.isBlank(cluster.getId()) ? UUID.randomUUID().toString() : cluster.getId());
        this.updateAndSaveClusterEntity(clusterEntity, cluster);
        return clusterEntity.getId();
    }