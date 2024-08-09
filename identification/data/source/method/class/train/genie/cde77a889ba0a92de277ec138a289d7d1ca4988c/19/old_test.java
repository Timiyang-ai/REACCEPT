@Test
    public void testSetClusterInfoForJob() throws GenieException {
        final String id = UUID.randomUUID().toString();
        final String name = UUID.randomUUID().toString();
        this.service.setClusterInfoForJob(JOB_1_ID, id, name);
        final Job job = this.service.getJob(JOB_1_ID);
        Assert.assertEquals(id, job.getExecutionClusterId());
        Assert.assertEquals(name, job.getExecutionClusterName());
    }