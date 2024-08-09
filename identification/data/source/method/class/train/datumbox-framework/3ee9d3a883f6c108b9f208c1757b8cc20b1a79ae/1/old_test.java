@Test
    public void testExtractColumnValues() {
        TestUtils.log(this.getClass(), "extractColumnValues");
        RandomSingleton.getInstance().setSeed(TestConfiguration.RANDOM_SEED);
        DatabaseConfiguration dbConf = TestUtils.getDBConfig();
        
        Object column = "height";
        Dataset instance = new Dataset(dbConf);
        
        
        AssociativeArray xData1 = new AssociativeArray();
        xData1.put("height", 188.0);
        xData1.put("weight", 88.0);
        instance.add(new Record(xData1, null));
        
        AssociativeArray xData2 = new AssociativeArray();
        xData2.put("height", 189.0);
        xData2.put("weight", 89.0);
        instance.add(new Record(xData2, null));
        
        AssociativeArray xData3 = new AssociativeArray();
        xData3.put("height", 190.0);
        xData3.put("weight", null);
        instance.add(new Record(xData3, null));
        
        
        FlatDataList expResult = new FlatDataList(Arrays.asList(new Object[]{188.0,189.0,190.0}));
        FlatDataList result = instance.extractColumnValues(column);
        assertEquals(expResult, result);
    }