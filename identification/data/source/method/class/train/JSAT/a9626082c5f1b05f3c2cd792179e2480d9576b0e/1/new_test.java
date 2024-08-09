@Test
    public void testCluster_3args_1()
    {
        System.out.println("cluster");
        MiniBatchKMeans kMeans = new MiniBatchKMeans(new EuclideanDistance(), 50, 50, SeedSelectionMethods.SeedSelection.FARTHEST_FIRST);
        List<List<DataPoint>> clusters = kMeans.cluster(easyData10, 10, true);
        assertEquals(10, clusters.size());
        Set<Integer> seenBefore = new IntSet();
        for(List<DataPoint> cluster :  clusters)
        {
            int thisClass = cluster.get(0).getCategoricalValue(0);
            assertFalse(seenBefore.contains(thisClass));
            for(DataPoint dp : cluster)
                assertEquals(thisClass, dp.getCategoricalValue(0));
        }
    }