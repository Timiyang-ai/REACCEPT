public VM validate(Dataset testingData) {  
        
        if(GeneralConfiguration.DEBUG) {
            System.out.println("test()");
        }
        
        knowledgeBase.load();

        //validate the model with the testing data and update the validationMetrics
        VM validationMetrics = validateModel(testingData);
        
        return validationMetrics;
    }