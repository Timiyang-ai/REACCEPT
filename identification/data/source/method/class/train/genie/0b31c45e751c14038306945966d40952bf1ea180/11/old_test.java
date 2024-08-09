@Test
    public void testOnCreateOrUpdateJob() throws GenieException {
        Assert.assertNull(this.job.getId());
        Assert.assertNull(this.job.getClusterCriteriasString());
        Assert.assertNull(this.job.getCommandCriteriaString());
        //Simulate the call stack JPA will make on persist
        this.job.onCreateAuditable();
        this.job.onCreateOrUpdateCommonEntityFields();
        this.job.onCreateOrUpdateJob();
        Assert.assertNotNull(this.job.getId());
        Assert.assertNotNull(this.job.getClusterCriteriasString());
        Assert.assertNotNull(this.job.getCommandCriteriaString());
        Assert.assertTrue(this.job.getTags().contains(this.job.getId()));
        Assert.assertTrue(this.job.getTags().contains(NAME));
    }