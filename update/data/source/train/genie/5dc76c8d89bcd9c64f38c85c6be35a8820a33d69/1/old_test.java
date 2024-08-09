@Test
    public void testSetJobStatus() {
        final JobEntity localJobEntity = new JobEntity();

        // Times are null on initialization
        Assert.assertNull(localJobEntity.getStarted());
        Assert.assertNull(localJobEntity.getFinished());

        // start time is not zero on INIT, finish time is still 0
        localJobEntity.setJobStatus(JobStatus.INIT);
        Assert.assertNotNull(localJobEntity.getStarted());
        Assert.assertNull(localJobEntity.getFinished());
        final Date started = localJobEntity.getStarted();

        // Shouldn't affect finish time
        localJobEntity.setJobStatus(JobStatus.RUNNING);
        Assert.assertThat(localJobEntity.getStarted(), Matchers.is(started));
        Assert.assertNull(localJobEntity.getFinished());

        // finish time is non-zero on completion
        localJobEntity.setJobStatus(JobStatus.SUCCEEDED);
        Assert.assertThat(localJobEntity.getStarted(), Matchers.is(started));
        Assert.assertNotNull(localJobEntity.getFinished());

        final JobEntity jobEntity2 = new JobEntity();
        // Times are null on initialization
        Assert.assertNull(jobEntity2.getStarted());
        Assert.assertNull(jobEntity2.getFinished());

        // start time is not zero on INIT, finish time is still 0
        final String initMessage = "We're initializing";
        jobEntity2.setJobStatus(JobStatus.INIT, initMessage);
        Assert.assertNotNull(jobEntity2.getStarted());
        Assert.assertNull(jobEntity2.getFinished());
        Assert.assertEquals(initMessage, jobEntity2.getStatusMsg());

        // finish time is non-zero on completion
        final String successMessage = "Job Succeeded";
        jobEntity2.setJobStatus(JobStatus.SUCCEEDED, successMessage);
        Assert.assertEquals(successMessage, jobEntity2.getStatusMsg());
        Assert.assertNotNull(jobEntity2.getFinished());
    }