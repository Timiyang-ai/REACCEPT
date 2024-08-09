@Override
    public void updateCluster(
        @NotBlank(message = "No cluster id entered. Unable to update.")
        final String id,
        @NotNull(message = "No cluster information entered. Unable to update.")
        @Valid
        final com.netflix.genie.common.dto.Cluster updateCluster
    ) throws GenieException {
        log.debug("Called with id {} and cluster {}", id, updateCluster);
        if (!this.clusterRepo.exists(id)) {
            throw new GenieNotFoundException("No cluster exists with the given id. Unable to update.");
        }
        final Optional<String> updateId = updateCluster.getId();
        if (updateId.isPresent() && !id.equals(updateId.get())) {
            throw new GenieBadRequestException("Cluster id inconsistent with id passed in.");
        }

        //TODO: Move update of common fields to super classes
        this.updateAndSaveClusterEntity(this.clusterRepo.findOne(id), updateCluster);
    }