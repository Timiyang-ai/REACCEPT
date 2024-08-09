public ClassificationMetrics validate(Dataframe testingData) {
        logger.info("validate()");

        predict(testingData);

        return new ClassificationMetrics(testingData);
    }