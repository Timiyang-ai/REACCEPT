@Test
    public void testTrainC_List_Set()
    {
        System.out.println("trainC(List<DataPointPair>, Set<integer>)");
        stump.setPredicting(easyNumAtTrain.getPredicting());
        stump.trainC(easyNumAtTrain, new IntSet(Arrays.asList(0)));
        for(int i = 0; i < easyNumAtTest.size(); i++)
            assertEquals(easyNumAtTest.getDataPointCategory(i), stump.classify(easyNumAtTest.getDataPoint(i)).mostLikely());
    }