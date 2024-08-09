public ClassifierValidator.ValidationMetrics validate(Dataframe testingData) {
        logger.info("validate()");

        knowledgeBase.load();

        predict(testingData);

        return new ClassifierValidator().validate(testingData);
    }