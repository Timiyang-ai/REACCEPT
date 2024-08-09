@Test
    public void testCreateJob() throws GenieException {
        final Job job = new Job.Builder(JOB_1_NAME, JOB_1_USER, JOB_1_VERSION, JOB_1_COMMAND_ARGS)
            .withId(JOB_1_ID)
            .withStarted(new Date())
            .build();
        final JobRequestEntity jobRequestEntity = Mockito.mock(JobRequestEntity.class);
        final ArgumentCaptor<JobEntity> argument = ArgumentCaptor.forClass(JobEntity.class);

        Mockito.when(this.jobRequestRepo.findOne(Mockito.eq(JOB_1_ID))).thenReturn(jobRequestEntity);

        this.jobPersistenceService.createJob(job);

        Mockito.verify(jobRequestEntity).setJob(argument.capture());
        Assert.assertEquals(JOB_1_NAME, argument.getValue().getName());
        Assert.assertEquals(JOB_1_USER, argument.getValue().getUser());
        Assert.assertEquals(JOB_1_VERSION, argument.getValue().getVersion());
        Assert.assertEquals(JOB_1_ID, argument.getValue().getId());
        Assert.assertEquals(JOB_1_COMMAND_ARGS, argument.getValue().getCommandArgs());
    }