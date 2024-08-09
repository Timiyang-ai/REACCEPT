@Test
    public void testSetCommandInfoForJob() throws GenieException {
        final String id = UUID.randomUUID().toString();
        final String name = UUID.randomUUID().toString();
        this.service.setCommandInfoForJob(JOB_1_ID, id, name);
        final Job job = this.service.getJob(JOB_1_ID);
        Assert.assertEquals(id, job.getCommandId());
        Assert.assertEquals(name, job.getCommandName());
    }