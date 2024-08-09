@SuppressWarnings("serial")
    @Test
    public void testCompute1() {
        setupParameters();
        parameters.setInputDimensions(new int[] { 9 });
        parameters.setColumnDimensions(new int[] { 5 });
        parameters.setPotentialRadius(5);

        //This is 0.3 in Python version due to use of dense 
        // permanence instead of sparse (as it should be)
        parameters.setPotentialPct(0.5); 

        parameters.setGlobalInhibition(false);
        parameters.setLocalAreaDensity(-1.0);
        parameters.setNumActiveColumnsPerInhArea(3);
        parameters.setStimulusThreshold(1);
        parameters.setSynPermInactiveDec(0.01);
        parameters.setSynPermActiveInc(0.1);
        parameters.setMinPctOverlapDutyCycle(0.1);
        parameters.setMinPctActiveDutyCycle(0.1);
        parameters.setDutyCyclePeriod(10);
        parameters.setMaxBoost(10);
        parameters.setSynPermTrimThreshold(0);

        //This is 0.5 in Python version due to use of dense 
        // permanence instead of sparse (as it should be)
        parameters.setPotentialPct(1);

        parameters.setSynPermConnected(0.1);

        initSP();

        SpatialPooler mock = new SpatialPooler() {
            public int[] inhibitColumns(Connections c, double[] overlaps) {
                return new int[] { 0, 1, 2, 3, 4 };
            }
        };

        int[] inputVector = new int[] { 1, 0, 1, 0, 1, 0, 0, 1, 1 };
        int[] activeArray = new int[] { 0, 0, 0, 0, 0 };
        for(int i = 0;i < 20;i++) {
            mock.compute(mem, inputVector, activeArray, true, true);
        }

        for(int i = 0;i < mem.getNumColumns();i++) {
//            System.out.println(Arrays.toString((int[])mem.getConnectedCounts().getSlice(i)));
//            System.out.println(Arrays.toString(mem.getPotentialPools().get(i).getDensePermanences(mem)));
            assertTrue(Arrays.equals(inputVector, ((int[])mem.getConnectedCounts().getSlice(i))));
        }
    }