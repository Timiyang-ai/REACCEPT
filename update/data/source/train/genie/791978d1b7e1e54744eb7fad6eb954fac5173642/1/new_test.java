@Test
    public void testSetJobStatus() {
        final JobEntity localJobEntity = new JobEntity();

        // Times are null on initialization
        Assert.assertFalse(localJobEntity.getStarted().isPresent());
        Assert.assertFalse(localJobEntity.getFinished().isPresent());

        // start time is not zero on INIT, finish time is still 0
        localJobEntity.setJobStatus(JobStatus.INIT);
        Assert.assertTrue(localJobEntity.getStarted().isPresent());
        Assert.assertFalse(localJobEntity.getFinished().isPresent());
        final Instant started = localJobEntity.getStarted().orElseThrow(IllegalArgumentException::new);

        // Shouldn't affect finish time
        localJobEntity.setJobStatus(JobStatus.RUNNING);
        Assert.assertThat(localJobEntity.getStarted().orElseGet(RandomSuppliers.INSTANT), Matchers.is(started));
        Assert.assertFalse(localJobEntity.getFinished().isPresent());

        // finish time is non-zero on completion
        localJobEntity.setJobStatus(JobStatus.SUCCEEDED);
        Assert.assertThat(localJobEntity.getStarted().orElseGet(RandomSuppliers.INSTANT), Matchers.is(started));
        Assert.assertTrue(localJobEntity.getFinished().isPresent());

        final JobEntity jobEntity2 = new JobEntity();
        // Times are null on initialization
        Assert.assertFalse(jobEntity2.getStarted().isPresent());
        Assert.assertFalse(jobEntity2.getFinished().isPresent());

        // start time is not zero on INIT, finish time is still 0
        final String initMessage = "We're initializing";
        jobEntity2.setJobStatus(JobStatus.INIT, initMessage);
        Assert.assertTrue(jobEntity2.getStarted().isPresent());
        Assert.assertFalse(jobEntity2.getFinished().isPresent());
        Assert.assertEquals(initMessage, jobEntity2.getStatusMsg().orElseGet(RandomSuppliers.STRING));

        // finish time is non-zero on completion
        final String successMessage = "Job Succeeded";
        jobEntity2.setJobStatus(JobStatus.SUCCEEDED, successMessage);
        Assert.assertEquals(successMessage, jobEntity2.getStatusMsg().orElseGet(RandomSuppliers.STRING));
        Assert.assertTrue(jobEntity2.getFinished().isPresent());
    }