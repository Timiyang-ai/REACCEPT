@Test
    public void testPredict() {
        System.out.println("predict");
        
        Dataset trainingData = new Dataset();
        Record r1 = new Record();
        r1.getX().put("ml1", 5.0);
        r1.getX().put("ml2", 4.0);
        r1.getX().put("ml3", 4.5);
        r1.getX().put("vg1", 1.0);
        r1.getX().put("vg2", 1.5);
        r1.getX().put("vg3", 0.5);
        r1.setY("pizza");
        trainingData.add(r1);
        Record r2 = new Record();
        r2.getX().put("ml1", 3.5);
        r2.getX().put("ml2", 4.5);
        r2.getX().put("ml3", 4.0);
        r2.getX().put("vg1", 1.5);
        r2.getX().put("vg2", 1.0);
        r2.getX().put("vg3", 2);
        r2.setY("burger");
        trainingData.add(r2);
        Record r3 = new Record();
        r3.getX().put("ml1", 4.0);
        r3.getX().put("ml2", 3.0);
        r3.getX().put("ml3", 5.0);
        r3.getX().put("vg1", 4.0);
        r3.getX().put("vg2", 3.0);
        r3.getX().put("vg3", 2.0);
        r3.setY("beer");
        trainingData.add(r3);
        Record r4 = new Record();
        r4.getX().put("ml1", 4.5);
        r4.getX().put("ml2", 4.0);
        r4.getX().put("ml3", 4.5);
        r4.getX().put("vg1", 4.5);
        r4.getX().put("vg2", 2.5);
        r4.getX().put("vg3", 1.0);
        r4.setY("potato");
        trainingData.add(r4);
        Record r5 = new Record();
        r5.getX().put("ml1", 1.5);
        r5.getX().put("ml2", 1.0);
        r5.getX().put("ml3", 0.5);
        r5.getX().put("vg1", 4.5);
        r5.getX().put("vg2", 5.0);
        r5.getX().put("vg3", 4.0);
        r5.setY("salad");
        trainingData.add(r5);
        Record r6 = new Record();
        r6.getX().put("ml1", 0.5);
        r6.getX().put("ml2", 0.5);
        r6.getX().put("ml3", 1.0);
        r6.getX().put("vg1", 4.0);
        r6.getX().put("vg2", 4.5);
        r6.getX().put("vg3", 5.0);
        r6.setY("risecookie");
        trainingData.add(r6);
        Record r7 = new Record();
        r7.getX().put("ml1", 4.0);
        r7.getX().put("ml2", 1.5);
        r7.getX().put("ml3", 3.0);
        r7.getX().put("vg1", 1.0);
        r7.getX().put("vg2", 5.0);
        r7.getX().put("vg3", 4.5);
        r7.setY("sparklewatter");
        trainingData.add(r7);
        Record r8 = new Record();
        r8.getX().put("ml1", 1.0);
        r8.getX().put("ml2", 1.0);
        r8.getX().put("ml3", 0.5);
        r8.getX().put("vg1", 4.0);
        r8.getX().put("vg2", 3.5);
        r8.getX().put("vg3", 5.0);
        r8.setY("rise");
        trainingData.add(r8);
        Record r9 = new Record();
        r9.getX().put("ml1", 3.0);
        r9.getX().put("ml2", 2.0);
        r9.getX().put("ml3", 1.0);
        r9.getX().put("vg1", 4.5);
        r9.getX().put("vg2", 4.5);
        r9.getX().put("vg3", 5.0);
        r9.setY("tea");
        trainingData.add(r9);
        Record r10 = new Record();
        r10.getX().put("ml1", 3.5);
        r10.getX().put("ml2", 5.0);
        r10.getX().put("ml3", 4.0);
        r10.getX().put("vg1", 1.5);
        r10.getX().put("vg2", 2.0);
        r10.getX().put("vg3", 2.5);
        r10.setY("chocolate");
        trainingData.add(r10);
        Record r11 = new Record();
        r11.getX().put("ml1", 5.0);
        r11.getX().put("ml2", 5.0);
        r11.getX().put("ml3", 5.0);
        r11.getX().put("vg1", 0.5);
        r11.getX().put("vg2", 0.5);
        r11.getX().put("vg3", 0.5);
        r11.setY("pitta");
        trainingData.add(r11);
        
        Dataset newData = new Dataset();
        Record profileData = new Record();
        profileData.getX().put("pizza", 4.5);
        profileData.getX().put("beer", 5);
        profileData.getX().put("salad", 0.5);
        newData.add(profileData);
        
        
        
        
        String dbName = "JUnitRecommender";
        CollaborativeFiltering instance = new CollaborativeFiltering(dbName, TestConfiguration.getDBConfig());
        
        CollaborativeFiltering.TrainingParameters param = new CollaborativeFiltering.TrainingParameters();
        param.setSimilarityMethod(CollaborativeFiltering.TrainingParameters.SimilarityMeasure.PEARSONS_CORRELATION);
        
        instance.fit(trainingData, param);
        
        
        instance = null;
        instance = new CollaborativeFiltering(dbName, TestConfiguration.getDBConfig());
        
        instance.predict(newData);
        
        Map<Object, Double> expResult = new HashMap<>();
        expResult.put("potato", 4.7779023488181);
        expResult.put("pitta", 4.6745332285272);
        expResult.put("burger", 4.649291902095);
        expResult.put("chocolate", 4.5922134578209);
        expResult.put("sparklewatter", 0.5);
        expResult.put("rise", 0.5);
        expResult.put("risecookie", 0.5);
        expResult.put("tea", 0.5);
        
        
        AssociativeArray result = newData.iterator().next().getYPredictedProbabilities();
        for(Map.Entry<Object, Object> entry : result.entrySet()) {
            assertEquals(expResult.get(entry.getKey()), Dataset.toDouble(entry.getValue()), TestConfiguration.DOUBLE_ACCURACY_HIGH);
        }
        
        
        instance.erase();
    }