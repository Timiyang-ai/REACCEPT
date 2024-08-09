@Test
    public void testSetApplicationInfoForJob() throws GenieException {
        final String id = UUID.randomUUID().toString();
        final String name = UUID.randomUUID().toString();
        this.service.setApplicationInfoForJob(JOB_1_ID, id, name);
        final Job job = this.service.getJob(JOB_1_ID);
        Assert.assertEquals(id, job.getApplicationId());
        Assert.assertEquals(name, job.getApplicationName());
    }