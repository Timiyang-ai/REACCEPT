@Override
    @Transactional
    public List<Cluster> chooseClusterForJobRequest(
            @NotBlank(message = "No job id entered. Unable to continue.")
            final JobRequest jobRequest
    ) throws GenieException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Called");
        }

        final List<ClusterCriteria> clusterCriterias = jobRequest.getClusterCriterias();
        final Set<String> commandCriteria = jobRequest.getCommandCriteria();

        for (final ClusterCriteria clusterCriteria : clusterCriterias) {
            @SuppressWarnings("unchecked")
            final List<ClusterEntity> clusterEntities = this.clusterRepo.findAll(
                    JpaClusterSpecs.findByClusterAndCommandCriteria(
                            clusterCriteria,
                            commandCriteria
                    )
            );

            if (!clusterEntities.isEmpty()) {
                return clusterEntities
                        .stream()
                        .map(ClusterEntity::getDTO)
                        .collect(Collectors.toList());
            }
        }

        //if we've gotten to here no clusters were found so return empty list
        return new ArrayList<>();
    }