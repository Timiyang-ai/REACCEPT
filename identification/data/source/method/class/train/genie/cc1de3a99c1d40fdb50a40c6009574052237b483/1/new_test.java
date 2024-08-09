@Test
    public void testSetJobStatus() {
        Job job = new Job();
        Date dt = new Date(0);
        
        // finish time is 0 on initialization
        Assert.assertTrue(dt.compareTo(job.getFinished()) == 0);

        // start time is not zero on INIT, finish time is still 0
        job.setJobStatus(JobStatus.INIT);
        Assert.assertNotNull(job.getStarted());
        Assert.assertTrue(dt.compareTo(job.getFinished()) == 0);

        // finish time is non-zero on completion
        job.setJobStatus(JobStatus.SUCCEEDED);
        Assert.assertFalse(dt.compareTo(job.getFinished()) == 0);
    }