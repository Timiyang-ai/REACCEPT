@Test
    public void testValidate() {
        logger.info("validate");
        
        DatabaseConfiguration dbConf = TestUtils.getDBConfig();
        
        
        String dbName = this.getClass().getSimpleName();

        
        Map<Object, URI> dataset = new HashMap<>();
        try {
            dataset.put("negative", TestUtils.getRemoteFile(new URL("http://www.datumbox.com/files/datasets/example.neg")));
            dataset.put("positive", TestUtils.getRemoteFile(new URL("http://www.datumbox.com/files/datasets/example.pos")));
        }
        catch(UncheckedIOException | MalformedURLException ex) {
            logger.warn("Unable to download datasets, skipping test.");
            throw new RuntimeException(ex);
        }
        
        UniqueWordSequenceExtractor wsExtractor = new UniqueWordSequenceExtractor(new UniqueWordSequenceExtractor.Parameters());
        
        Dataframe trainingData = Dataframe.Builder.parseTextFiles(dataset, wsExtractor, dbConf);
        
        
        LatentDirichletAllocation lda = new LatentDirichletAllocation(dbName, dbConf);
        
        LatentDirichletAllocation.TrainingParameters trainingParameters = new LatentDirichletAllocation.TrainingParameters();
        trainingParameters.setMaxIterations(15);
        trainingParameters.setAlpha(0.01);
        trainingParameters.setBeta(0.01);
        trainingParameters.setK(25);        
        
        lda.fit(trainingData, trainingParameters);
        
        lda.validate(trainingData);
        
        Dataframe reducedTrainingData = new Dataframe(dbConf);
        for(Record r : trainingData) {
            //take the topic assignments and convert them into a new Record
            reducedTrainingData.add(new Record(r.getYPredictedProbabilities(), r.getY()));
        }
        
        SoftMaxRegression smr = new SoftMaxRegression(dbName, dbConf);
        SoftMaxRegression.TrainingParameters tp = new SoftMaxRegression.TrainingParameters();
        tp.setLearningRate(1.0);
        tp.setTotalIterations(50);
        
        AbstractClassifier.ValidationMetrics vm = smr.kFoldCrossValidation(reducedTrainingData, tp, 1);
        
        double expResult = 0.6859007513066202;
        double result = vm.getMacroF1();
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);

        smr.delete();
        lda.delete();
        reducedTrainingData.delete();
        
        trainingData.delete();
    }