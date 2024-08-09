@Override
    public AssignmentCounts getAssignmentCounts(Experiment.ID experimentID, Context context) {

        // Uses counters
        Experiment experiment = cassandraRepository.getExperiment(experimentID);
        if (Objects.isNull(experiment)) {
            throw new ExperimentNotFoundException(experimentID);
        }            
        return assignmentRepository.getBucketAssignmentCount(experiment);
    }