@Test
    public void testCopy() {
        HashMap<String, NamedTuple> classifierInput = new HashMap<>();
        NamedTuple classifiers = new NamedTuple(new String[] { "one", "two" }, 1, 2);
        Object layerInput = new Object();
        int[] sdr = new int[] { 20 };
        int[] encoding = new int[40];
        int[] activeColumns = new int[25];
        int[] sparseActives = new int[2];
        Set<Cell> previousPrediction = new LinkedHashSet<>(); previousPrediction.add(new Cell(new Column(4, 0), 2));
        Set<Cell> currentPrediction = new LinkedHashSet<>(); currentPrediction.add(new Cell(new Column(4, 0), 3));
        ClassifierResult<Object> classification = new ClassifierResult<>();
        double anomalyScore = 0.48d;
        Object customObject = new Network("", NetworkTestHarness.getNetworkDemoTestEncoderParams());
        
        ManualInput mi = new ManualInput()
        .classifierInput(classifierInput)
        .layerInput(layerInput)
        .sdr(sdr)
        .encoding(encoding)
        .feedForwardActiveColumns(activeColumns)
        .feedForwardSparseActives(sparseActives)
        .predictiveCells(previousPrediction)
        .predictiveCells(currentPrediction) // last prediction internally becomes previous
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
        
        assertTrue(Arrays.equals(copy.getFeedForwardActiveColumns(), activeColumns));
        assertFalse(copy.getFeedForwardActiveColumns() == activeColumns);
        
        assertTrue(Arrays.equals(copy.getFeedForwardSparseActives(), sparseActives));
        assertFalse(copy.getFeedForwardSparseActives() == sparseActives);
        
        assertTrue(copy.getPredictiveCells().equals(currentPrediction));
        assertFalse(copy.getPredictiveCells() == currentPrediction);
        
        assertTrue(copy.getPreviousPredictiveCells().equals(previousPrediction));
        assertFalse(copy.getPreviousPredictiveCells() == previousPrediction);
        
        assertTrue(copy.getClassifiers().equals(classifiers));
        assertFalse(copy.getClassifiers() == classifiers);
        
        assertTrue(copy.getClassification("foo").equals(classification));
        
        assertEquals(copy.getAnomalyScore(), anomalyScore, 0.0); // zero deviation
        
        assertEquals(copy.getCustomObject(), customObject);
    }