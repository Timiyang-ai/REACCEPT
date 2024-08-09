@Override
    @Transactional(readOnly = true)
    public Cluster getCluster(
            @NotBlank(message = "No id entered. Unable to get.")
            final String id
    ) throws GenieException {
        LOG.debug("Called with id " + id);
        final Cluster cluster = this.clusterRepo.findOne(id);
        if (cluster != null) {
            return cluster;
        } else {
            throw new GenieNotFoundException("No cluster with id " + id + " exists.");
        }
    }