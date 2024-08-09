@Test
    public void testSetJobStatus() {
        Job job = new Job();

        // finish time is 0 on initialization
        Assert.assertTrue(0L == job.getFinishTime());

        // start time is not zero on INIT, finish time is still 0
        job.setJobStatus(Types.JobStatus.INIT);
        Assert.assertNotNull(job.getStartTime());
        Assert.assertTrue(0L == job.getFinishTime());

        // finish time is non-zero on completion
        job.setJobStatus(Types.JobStatus.SUCCEEDED);
        Assert.assertFalse(0L == job.getFinishTime());
    }