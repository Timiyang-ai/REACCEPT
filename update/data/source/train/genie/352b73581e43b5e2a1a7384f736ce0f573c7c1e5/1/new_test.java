@Test
    public void testSetJobStatus() {
        final JobEntity localJobEntity = new JobEntity();
        final Date dt = new Date(0);

        // finish time is 0 on initialization
        Assert.assertTrue(dt.compareTo(localJobEntity.getFinished()) == 0);

        // start time is not zero on INIT, finish time is still 0
        localJobEntity.setJobStatus(JobStatus.INIT);
        Assert.assertNotNull(localJobEntity.getStarted());
        Assert.assertTrue(dt.compareTo(localJobEntity.getFinished()) == 0);

        // Shouldn't affect finish time
        localJobEntity.setJobStatus(JobStatus.RUNNING);
        Assert.assertTrue(dt.compareTo(localJobEntity.getFinished()) == 0);

        // finish time is non-zero on completion
        localJobEntity.setJobStatus(JobStatus.SUCCEEDED);
        Assert.assertFalse(dt.compareTo(localJobEntity.getFinished()) == 0);

        final JobEntity jobEntity2 = new JobEntity();
        // finish time is 0 on initialization
        Assert.assertTrue(dt.compareTo(jobEntity2.getFinished()) == 0);

        // start time is not zero on INIT, finish time is still 0
        final String initMessage = "We're initializing";
        jobEntity2.setJobStatus(JobStatus.INIT, initMessage);
        Assert.assertNotNull(jobEntity2.getStarted());
        Assert.assertEquals(initMessage, jobEntity2.getStatusMsg());
        Assert.assertTrue(dt.compareTo(jobEntity2.getStarted()) == -1);
        Assert.assertTrue(dt.compareTo(jobEntity2.getFinished()) == 0);

        // finish time is non-zero on completion
        final String successMessage = "Job Succeeded";
        jobEntity2.setJobStatus(com.netflix.genie.common.dto.JobStatus.SUCCEEDED, successMessage);
        Assert.assertEquals(successMessage, jobEntity2.getStatusMsg());
        Assert.assertFalse(dt.compareTo(jobEntity2.getFinished()) == 0);
    }