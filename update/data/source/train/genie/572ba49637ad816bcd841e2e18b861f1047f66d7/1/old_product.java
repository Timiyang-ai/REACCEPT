@Override
    @Transactional
    public List<Cluster> chooseClusterForJob(
            @NotBlank(message = "No job id entered. Unable to continue.")
            final String jobId
    ) throws GenieException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Called");
        }
        final JobEntity jobEntity = this.jobRepo.findOne(jobId);
        if (jobEntity == null) {
            throw new GenieNotFoundException("No job with id " + jobId + " exists. Unable to continue."
            );
        }

        final List<ClusterCriteria> clusterCriterias = jobEntity.getRequest().getClusterCriteriasAsList();
        final Set<String> commandCriteria = jobEntity.getRequest().getCommandCriteriaAsSet();

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