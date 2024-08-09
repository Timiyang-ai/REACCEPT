Experiment getExperimentIfExists(final Experiment.ID experimentID) {
        Experiment experiment = experiments.getExperiment(experimentID);
        if (Objects.isNull(experiment)) {
            throw new ExperimentNotFoundException(experimentID);
        }
        return experiment;
    }