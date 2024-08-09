@Test
    public void testSetJobStatus() throws GenieException {
        final String msg = UUID.randomUUID().toString();
        this.service.setJobStatus(JOB_1_ID, JobStatus.RUNNING, msg);
        final Job job = this.service.getJob(JOB_1_ID);
        Assert.assertEquals(JobStatus.RUNNING, job.getStatus());
        Assert.assertEquals(msg, job.getStatusMsg());
    }