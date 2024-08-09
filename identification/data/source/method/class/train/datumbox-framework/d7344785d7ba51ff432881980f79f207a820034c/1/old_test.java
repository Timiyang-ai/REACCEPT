@Test
    public void testPredict() {
        logger.info("testPredict");
        
        Configuration configuration = Configuration.getConfiguration();
        
        Dataframe[] data = Datasets.regressionNumeric(configuration);
        
        Dataframe trainingData = data[0];
        Dataframe validationData = data[1];
        
        String storageName = this.getClass().getSimpleName();
        DummyXYMinMaxNormalizer df = MLBuilder.create(new DummyXYMinMaxNormalizer.TrainingParameters(), configuration);
        df.fit_transform(trainingData);
        df.save(storageName);

        
        NLMS.TrainingParameters param = new NLMS.TrainingParameters();
        param.setTotalIterations(1600);
        param.setL1(0.00000001);



        NLMS instance = MLBuilder.create(param, configuration);
        instance.fit(trainingData);
        instance.save(storageName);

        df.denormalize(trainingData);
        trainingData.close();
        
        instance.close();
        df.close();
        //instance = null;
        //df = null;
        
        df = MLBuilder.load(DummyXYMinMaxNormalizer.class, storageName, configuration);
        instance = MLBuilder.load(NLMS.class, storageName, configuration);

        df.transform(validationData);
        
        instance.predict(validationData);

        df.denormalize(validationData);
        
        for(Record r : validationData) {
            assertEquals(TypeInference.toDouble(r.getY()), TypeInference.toDouble(r.getYPredicted()), Constants.DOUBLE_ACCURACY_HIGH);
        }
        
        df.delete();
        instance.delete();

        validationData.close();
    }