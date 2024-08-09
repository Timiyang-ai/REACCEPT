@Override
    public ExperimentStatistics getExperimentStatistics(final Experiment.ID experimentId, final Parameters parameters) {
        return (ExperimentStatistics) transactionFactory.transaction(new Block() {
            @Override
            public Object value(Transaction transaction) {
                ExperimentCounts counts = getExperimentRollup(experimentId, parameters);

                return calculateExperimentStatistics(counts, parameters.getMetricImpl(), parameters.getEffectSize(),
                        parameters.getMode());
            }
        });
    }