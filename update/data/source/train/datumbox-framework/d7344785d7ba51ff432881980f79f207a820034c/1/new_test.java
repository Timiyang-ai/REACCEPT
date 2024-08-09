@Test
    public void testPredict() {
        logger.info("testPredict");
        
        Configuration configuration = Configuration.getConfiguration();
        
        Dataframe[] data = Datasets.regressionNumeric(configuration);
        
        Dataframe trainingData = data[0];
        Dataframe validationData = data[1];
        
        String storageName = this.getClass().getSimpleName();


        MinMaxScaler.TrainingParameters nsParams = new MinMaxScaler.TrainingParameters();
        nsParams.setScaleResponse(true);
        MinMaxScaler numericalScaler = MLBuilder.create(nsParams, configuration);

        numericalScaler.fit_transform(trainingData);
        numericalScaler.save(storageName);

        CornerConstraintsEncoder.TrainingParameters ceParams = new CornerConstraintsEncoder.TrainingParameters();
        CornerConstraintsEncoder categoricalEncoder = MLBuilder.create(ceParams, configuration);

        categoricalEncoder.fit_transform(trainingData);
        categoricalEncoder.save(storageName);

        
        NLMS.TrainingParameters param = new NLMS.TrainingParameters();
        param.setTotalIterations(1600);
        param.setL1(0.00000001);



        NLMS instance = MLBuilder.create(param, configuration);
        instance.fit(trainingData);
        instance.save(storageName);

        trainingData.close();
        
        instance.close();
        numericalScaler.close();
        categoricalEncoder.close();



        numericalScaler = MLBuilder.load(MinMaxScaler.class, storageName, configuration);
        categoricalEncoder = MLBuilder.load(CornerConstraintsEncoder.class, storageName, configuration);
        instance = MLBuilder.load(NLMS.class, storageName, configuration);

        numericalScaler.transform(validationData);
        categoricalEncoder.transform(validationData);
        
        instance.predict(validationData);

        for(Record r : validationData) {
            assertEquals(TypeInference.toDouble(r.getY()), TypeInference.toDouble(r.getYPredicted()), Constants.DOUBLE_ACCURACY_HIGH);
        }

        numericalScaler.delete();
        categoricalEncoder.delete();
        instance.delete();

        validationData.close();
    }