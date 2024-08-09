@Test
    public void testCluster_3args_1()
    {
        System.out.println("cluster");
        NaiveKMeans kMeans = new NaiveKMeans(new EuclideanDistance(), SeedSelectionMethods.SeedSelection.FARTHEST_FIRST);
        int[] assignment = new int[easyData10.getSampleSize()];
        kMeans.cluster(easyData10, null, 10, seeds, assignment, true, true, true, null);
        List<List<DataPoint>> clusters = KClustererBase.createClusterListFromAssignmentArray(assignment, easyData10);
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