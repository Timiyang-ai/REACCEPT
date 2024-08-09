    @Test
    public void test_medoid()
    {
        System.out.println("cluster(dataset, int)");
        //Use a deterministic seed initialization. Lets see that the new method does LESS distance computations
        DistanceCounter dm = new DistanceCounter(new EuclideanDistance());
        
        //MEDDIT works best when dimenion is higher, and poorly when dimension is low. So lets put it in the happy area
        GridDataGenerator gdg = new GridDataGenerator(new Normal(0, 0.1), RandomUtil.getRandom(), 2, 2, 2, 2);

        
        
        List<Vec> X = gdg.generateData(500).getDataVectors();
        double tol = 0.01;
        
        int tureMed = PAM.medoid(true, X, dm);
        long pamD = dm.getCallCount();
        
        dm.resetCounter();
        for(boolean parallel : new boolean[]{false, true})
        {
            dm.resetCounter();
            int approxMed = MEDDIT.medoid(parallel, X, tol , dm);
            
            
            assertEquals(tureMed, approxMed);
            
            assertTrue(pamD > dm.getCallCount());
        }
    }