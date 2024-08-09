private <ML extends AbstractClassifier, FS extends AbstractFeatureSelector> void trainAndValidate(
            ML.AbstractTrainingParameters modelerTrainingParameters,
            FS.AbstractTrainingParameters featureSelectorTrainingParameters,
            double expectedF1score,
            int testId) {
        Configuration configuration = Configuration.getConfiguration();
        
        
        String storageName = this.getClass().getSimpleName() + testId;
        
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

        //numerical scaling configuration
        trainingParameters.setNumericalScalerTrainingParameters(null);

        //categorical encoding configuration
        trainingParameters.setCategoricalEncoderTrainingParameters(null);
        
        //feature selection configuration
        trainingParameters.setFeatureSelectorTrainingParameters(featureSelectorTrainingParameters);

        //classifier configuration
        trainingParameters.setModelerTrainingParameters(modelerTrainingParameters);
        
        //text extraction configuration
        NgramsExtractor.Parameters exParams = new NgramsExtractor.Parameters();
        exParams.setMaxDistanceBetweenKwds(2);
        exParams.setExaminationWindowLength(6);
        trainingParameters.setTextExtractorParameters(exParams);

        TextClassifier instance = MLBuilder.create(trainingParameters, configuration);
        instance.fit(dataset);
        instance.save(storageName);


        ClassificationMetrics vm = instance.validate(dataset);
        assertEquals(expectedF1score, vm.getMacroF1(), Constants.DOUBLE_ACCURACY_HIGH);

        instance.close();
        
        
        
        instance = MLBuilder.load(TextClassifier.class, storageName, configuration);
        Dataframe validationData;
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
        validationData.close();
    }