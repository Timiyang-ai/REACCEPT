@Test
    public void testKillJob() throws GenieException {
        final Calendar one = Calendar.getInstance();
        one.clear();
        one.set(2014, Calendar.JANUARY, 2, 1, 50, 0);
        final Calendar two = Calendar.getInstance();
        two.clear();
        two.set(2014, Calendar.JANUARY, 3, 1, 50, 0);
        final Calendar three = Calendar.getInstance();
        three.clear();
        three.set(2014, Calendar.JANUARY, 4, 1, 50, 0);
        // should return immediately despite bogus killURI
        final Job job1 = this.xs.killJob(JOB_1_ID);
        Assert.assertEquals(JobStatus.SUCCEEDED, job1.getStatus());
        Assert.assertEquals(one.getTimeInMillis(), job1.getUpdated().getTime());
        final Job job2 = this.xs.killJob(JOB_2_ID);
        Assert.assertEquals(JobStatus.KILLED, job2.getStatus());
        Assert.assertEquals(two.getTimeInMillis(), job2.getUpdated().getTime());
        final Job job3 = this.xs.killJob(JOB_3_ID);
        Assert.assertEquals(JobStatus.FAILED, job3.getStatus());
        Assert.assertEquals(three.getTimeInMillis(), job3.getUpdated().getTime());
    }