@Test
    public void testOnLoadJob() throws GenieException {
        this.job.onCreateOrUpdateJob();
        final String clusterCriteriasString = this.job.getClusterCriteriasString();
        final String commandCriteriaString = this.job.getCommandCriteriaString();
        final Job job2 = new Job();
        job2.setClusterCriteriasString(clusterCriteriasString);
        job2.setCommandCriteriaString(commandCriteriaString);
        job2.onLoadJob();
        Assert.assertEquals(CLUSTER_CRITERIAS.size(), job2.getClusterCriterias().size());
        Assert.assertEquals(COMMAND_CRITERIA, job2.getCommandCriteria());
    }