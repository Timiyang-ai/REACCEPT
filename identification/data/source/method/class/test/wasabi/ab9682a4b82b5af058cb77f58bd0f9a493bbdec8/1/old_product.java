Experiment getExperimentIfExists(final Experiment.ID experimentID) {
        Experiment experiment = experiments.getExperiment(experimentID);
        if (experiment == null) {
            throw new ExperimentNotFoundException(experimentID);
        }
        return experiment;
    }