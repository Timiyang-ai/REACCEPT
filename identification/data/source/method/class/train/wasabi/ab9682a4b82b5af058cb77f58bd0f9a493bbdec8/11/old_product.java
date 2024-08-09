@Override
    public AssignmentCounts getAssignmentCounts(Experiment.ID experimentID, Context context) {

        // Uses counters
        Experiment experiment = cassandraRepository.getExperiment(experimentID);
        if (release_date != null && release_date.before(experiment.getCreationTime())) {
            return assignmentRepository.getBucketAssignmentCount(experiment);
        } else {
            if (Objects.isNull(experiment)) {
                throw new ExperimentNotFoundException(experimentID);
            }            
            return cassandraRepository.getAssignmentCounts(experimentID, context);
        }
    }