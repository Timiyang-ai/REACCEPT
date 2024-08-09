@Test
    public void testGetColumns() {
        TestUtils.log(this.getClass(), "getColumns");
        RandomSingleton.getInstance().setSeed(TestConfiguration.RANDOM_SEED);
        DatabaseConfiguration dbConf = TestUtils.getDBConfig();
        
        Dataset instance = new Dataset(dbConf);
        
        AssociativeArray xData1 = new AssociativeArray();
        xData1.put("1", true);
        instance.add(new Record(xData1, null));
        
        AssociativeArray xData2 = new AssociativeArray();
        xData2.put("2", 1.0);
        instance.add(new Record(xData2, null));
        
        AssociativeArray xData3 = new AssociativeArray();
        xData3.put("3", (short)1);
        instance.add(new Record(xData3, null));
        
        AssociativeArray xData4 = new AssociativeArray();
        xData4.put("4", "s");
        instance.add(new Record(xData4, null));
        
        Map<Object, Dataset.ColumnType> expResult = new LinkedHashMap<>();
        expResult.put("1", Dataset.ColumnType.DUMMYVAR);
        expResult.put("2", Dataset.ColumnType.NUMERICAL);
        expResult.put("3", Dataset.ColumnType.ORDINAL);
        expResult.put("4", Dataset.ColumnType.CATEGORICAL);
        Map<Object, Dataset.ColumnType> result = instance.getColumns();
        assertEquals(expResult, result);
    }