private <ML extends AbstractClassifier, FS extends AbstractFeatureSelector> void trainAndValidate(
            Class<ML> modelerClass, 
            ML.AbstractTrainingParameters modelerTrainingParameters,
            Class<FS> featureSelectorClass, 
            FS.AbstractTrainingParameters featureSelectorTrainingParameters,
            double expectedF1score) {
        Configuration conf = Configuration.getConfiguration();
        
        
        String dbName = this.getClass().getSimpleName();
        
        Map<Object, URI> dataset = new HashMap<>();
        try {
            dataset.put("negative", this.getClass().getClassLoader().getResource("datasets/sentimentAnalysis.neg.txt").toURI());
            dataset.put("positive", this.getClass().getClassLoader().getResource("datasets/sentimentAnalysis.pos.txt").toURI());
        }
        catch(UncheckedIOException | URISyntaxException ex) {
            logger.warn("Unable to download datasets, skipping test.");
            throw new RuntimeException(ex);
        }

        TextClassifier.TrainingParameters trainingParameters = new TextClassifier.TrainingParameters();
        
        //Classifier configuration
        trainingParameters.setModelerTrainingParameters(modelerTrainingParameters);
        
        //data transfomation configuration
        trainingParameters.setDataTransformerTrainingParameters(null);
        
        //feature selection configuration
        trainingParameters.setFeatureSelectorTrainingParameters(featureSelectorTrainingParameters);
        
        //text extraction configuration
        NgramsExtractor.Parameters exParams = new NgramsExtractor.Parameters();
        exParams.setMaxDistanceBetweenKwds(2);
        exParams.setExaminationWindowLength(6);
        trainingParameters.setTextExtractorParameters(exParams);

        TextClassifier instance = new TextClassifier(dbName, conf, trainingParameters);
        instance.fit(dataset);


        ClassificationMetrics vm = instance.validate(dataset);
        assertEquals(expectedF1score, vm.getMacroF1(), Constants.DOUBLE_ACCURACY_HIGH);

        instance.close();
        //instance = null;
        
        
        
        instance = new TextClassifier(dbName, conf);
        Dataframe validationData = null;
        try {
            validationData = instance.predict(this.getClass().getClassLoader().getResource("datasets/sentimentAnalysis.unlabelled.txt").toURI());
        }
        catch(UncheckedIOException | URISyntaxException ex) {
            logger.warn("Unable to download datasets, skipping test.");
            throw new RuntimeException(ex);
        }
        
        List<Object> expResult = Arrays.asList("negative","positive");
        int i = 0;
        for(Record r : validationData.values()) {
            assertEquals(expResult.get(i), r.getYPredicted());
            ++i;
        }
        
        instance.delete();
        validationData.delete();
    }