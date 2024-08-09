public VM validate(Dataframe testingData) {  
        logger.info("validate()");
        
        kb().load();

        //validate the model with the testing data and update the validationMetrics
        VM validationMetrics = validateModel(testingData);
        
        return validationMetrics;
    }