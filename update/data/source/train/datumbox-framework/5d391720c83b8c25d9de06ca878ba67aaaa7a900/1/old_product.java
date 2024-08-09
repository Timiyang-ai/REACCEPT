private <ML extends AbstractClassifier, FS extends AbstractFeatureSelector, NS extends AbstractScaler> void trainAndValidate(
            ML.AbstractTrainingParameters modelerTrainingParameters,
            FS.AbstractTrainingParameters featureSelectorTrainingParameters,
            NS.AbstractTrainingParameters numericalScalerTrainingParameters,
            double expectedF1score,
            int testId) {
        Configuration configuration = Configuration.getConfiguration();
        
        
        String storageName = this.getClass().getSimpleName() + testId;

        Map<Object, URI> dataset = Datasets.sentimentAnalysis();

        TextClassifier.TrainingParameters trainingParameters = new TextClassifier.TrainingParameters();

        //numerical scaling configuration
        trainingParameters.setNumericalScalerTrainingParameters(numericalScalerTrainingParameters);

        //categorical encoding configuration
        trainingParameters.setCategoricalEncoderTrainingParameters(null);
        
        //feature selection configuration
        trainingParameters.setFeatureSelectorTrainingParametersList(Arrays.asList(featureSelectorTrainingParameters));

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
        Dataframe validationData = instance.predict(Datasets.sentimentAnalysisUnlabeled());
        
        List<Object> expResult = Arrays.asList("negative","positive");
        int i = 0;
        for(Record r : validationData.values()) {
            assertEquals(expResult.get(i), r.getYPredicted());
            ++i;
        }
        
        instance.delete();
        validationData.close();
    }