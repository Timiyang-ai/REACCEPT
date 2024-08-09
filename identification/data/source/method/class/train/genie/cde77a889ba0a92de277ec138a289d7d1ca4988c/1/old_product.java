@Override
    @Transactional
    public List<Cluster> chooseClusterForJob(
            @NotBlank(message = "No job id entered. Unable to continue.")
            final String jobId
    ) throws GenieException {
        LOG.debug("Called");
        final Job job = this.jobRepo.findOne(jobId);
        if (job == null) {
            throw new GenieNotFoundException("No job with id " + jobId + " exists. Unable to continue."
            );
        }

        final List<ClusterCriteria> clusterCriterias = job.getClusterCriterias();
        final Set<String> commandCriteria = job.getCommandCriteria();

        for (final ClusterCriteria clusterCriteria : clusterCriterias) {
            @SuppressWarnings("unchecked")
            final List<Cluster> clusters = this.clusterRepo.findAll(
                    ClusterSpecs.findByClusterAndCommandCriteria(
                            clusterCriteria,
                            commandCriteria
                    )
            );

            if (!clusters.isEmpty()) {
                // Add the successfully criteria to the job object in string form.
                job.setChosenClusterCriteriaString(
                        StringUtils.join(
                                clusterCriteria.getTags(),
                                CRITERIA_DELIMITER
                        )
                );
                return clusters;
            }
        }

        //if we've gotten to here no clusters were found so return empty list
        return new ArrayList<>();
    }