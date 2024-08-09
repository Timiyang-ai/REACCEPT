@Test
    public void testCopy() {
        HashMap<String, NamedTuple> classifierInput = new HashMap<>();
        NamedTuple classifiers = new NamedTuple(new String[] { "one", "two" }, 1, 2);
        Object layerInput = new Object();
        int[] sdr = new int[20];
        int[] encoding = new int[40];
        int[] activeColumns = new int[25];
        int[] sparseActives = new int[2];
        int[] previousPrediction = new int[4];
        int[] currentPrediction = new int[8];
        ClassifierResult<Object> classification = new ClassifierResult<>();
        double anomalyScore = 0.48d;
        Object customObject = new Network("", NetworkTestHarness.getNetworkDemoTestEncoderParams());
        
        ManualInput mi = new ManualInput()
        .classifierInput(classifierInput)
        .layerInput(layerInput)
        .sdr(sdr)
        .encoding(encoding)
        .activeColumns(activeColumns)
        .sparseActives(sparseActives)
        .predictedColumns(previousPrediction)
        .predictedColumns(currentPrediction) // last prediction internally becomes previous
        .classifiers(classifiers)
        .storeClassification("foo", classification)
        .anomalyScore(anomalyScore)
        .customObject(customObject);
        
        ManualInput copy = mi.copy();
        assertTrue(copy.getClassifierInput().equals(classifierInput));
        assertFalse(copy.getClassifierInput() == classifierInput);
        
        assertTrue(copy.getLayerInput() == layerInput);
        
        assertTrue(Arrays.equals(copy.getSDR(), sdr));
        assertFalse(copy.getSDR() == sdr);
        
        assertTrue(Arrays.equals(copy.getEncoding(), encoding));
        assertFalse(copy.getEncoding() == encoding);
        
        assertTrue(Arrays.equals(copy.getActiveColumns(), activeColumns));
        assertFalse(copy.getActiveColumns() == activeColumns);
        
        assertTrue(Arrays.equals(copy.getSparseActives(), sparseActives));
        assertFalse(copy.getSparseActives() == sparseActives);
        
        assertTrue(Arrays.equals(copy.getPredictedColumns(), currentPrediction));
        assertFalse(copy.getPredictedColumns() == currentPrediction);
        
        assertTrue(Arrays.equals(copy.getPreviousPrediction(), previousPrediction));
        assertFalse(copy.getPreviousPrediction() == previousPrediction);
        
        assertTrue(copy.getClassifiers().equals(classifiers));
        assertFalse(copy.getClassifiers() == classifiers);
        
        assertTrue(copy.getClassification("foo").equals(classification));
        
        assertEquals(copy.getAnomalyScore(), anomalyScore, 0.0); // zero deviation
        
        assertEquals(copy.getCustomObject(), customObject);
    }