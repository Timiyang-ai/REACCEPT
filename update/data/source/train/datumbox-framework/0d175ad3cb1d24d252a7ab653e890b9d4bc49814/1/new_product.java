public ClassifierValidator.ValidationMetrics validate(Dataframe testingData) {
        logger.info("validate()");

        predict(testingData);

        return new ClassifierValidator().validate(testingData);
    }