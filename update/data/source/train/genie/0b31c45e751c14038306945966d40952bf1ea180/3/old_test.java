@Test
    public void testStringToClusterCriterias() throws GenieException {
        final List<ClusterCriteria> criterias =
                this.job.stringToClusterCriterias(EXPECTED_CLUSTER_CRITERIAS_STRING);
        Assert.assertEquals(CLUSTER_CRITERIAS.size(), criterias.size());
        for (int i = 0; i < criterias.size(); i++) {
            Assert.assertEquals(CLUSTER_CRITERIAS.get(i).getTags(),
                    criterias.get(i).getTags());
        }
    }