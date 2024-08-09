public VM validate(Dataset testingData) {  
        logger.debug("test()");
        
        knowledgeBase.load();

        //validate the model with the testing data and update the validationMetrics
        VM validationMetrics = validateModel(testingData);
        
        return validationMetrics;
    }