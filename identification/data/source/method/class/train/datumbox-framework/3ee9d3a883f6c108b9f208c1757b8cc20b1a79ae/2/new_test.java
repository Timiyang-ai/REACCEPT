@Test
    public void testExtractColumnValuesByY() {
        TestUtils.log(this.getClass(), "extractColumnValuesByY");
        RandomSingleton.getInstance().setSeed(TestConfiguration.RANDOM_SEED);
        DatabaseConfiguration dbConf = TestUtils.getDBConfig();
        
        Object column = "height";
        Dataset instance = new Dataset(dbConf);
        
        AssociativeArray xData1 = new AssociativeArray();
        xData1.put("height", 188.0);
        xData1.put("weight", 88.0);
        instance.add(new Record(xData1, "Class1"));
        
        AssociativeArray xData2 = new AssociativeArray();
        xData2.put("height", 189.0);
        xData2.put("weight", 89.0);
        instance.add(new Record(xData2, "Class1"));
        
        AssociativeArray xData3 = new AssociativeArray();
        xData3.put("height", 190.0);
        xData3.put("weight", null);
        instance.add(new Record(xData3, "Class2"));
        
        
        TransposeDataList expResult = new TransposeDataList();
        expResult.put("Class1", new FlatDataList(Arrays.asList(new Object[]{188.0,189.0})));
        expResult.put("Class2", new FlatDataList(Arrays.asList(new Object[]{190.0})));
        TransposeDataList result = instance.extractXColumnValuesByY(column);
        assertEquals(expResult, result);
    }