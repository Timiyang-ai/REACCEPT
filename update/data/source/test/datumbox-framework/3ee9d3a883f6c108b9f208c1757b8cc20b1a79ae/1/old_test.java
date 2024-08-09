@Test
    public void testMedian() {
        TestUtils.log(this.getClass(), "median");
        DataTable2D classifierClassProbabilityMatrix = getClassifierClassProbabilityMatrix();
        
        AssociativeArray expResult = new AssociativeArray();
        expResult.put("class1", 0.2665);
        expResult.put("class2", 0.45);
        expResult.put("class3", 0.3165);
        
        AssociativeArray result = FixedCombinationRules.median(classifierClassProbabilityMatrix);
        for(Object k: expResult.keySet()) {
            assertEquals(TypeConversions.toDouble(expResult.get(k)), TypeConversions.toDouble(result.get(k)), TestConfiguration.DOUBLE_ACCURACY_HIGH);
        }
    }