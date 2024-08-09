@Test
    public void testCreateJobExecution() throws GenieException {
        final String hostname = "hostname";
        final int pid = 123;
        final long checkDelay = 3000L;
        final Date timeout = new Date();
        final JobExecution jobExecution = new JobExecution.Builder(hostname, pid, checkDelay, timeout)
            .withId(JOB_1_ID)
            .build();
        final JobEntity jobEntity = Mockito.mock(JobEntity.class);
        Mockito.when(this.jobRepo.findOne(Mockito.eq(JOB_1_ID))).thenReturn(jobEntity);
        final ArgumentCaptor<JobExecutionEntity> argument = ArgumentCaptor.forClass(JobExecutionEntity.class);
        final ArgumentCaptor<JobStatus> argument1 = ArgumentCaptor.forClass(JobStatus.class);

        this.jobPersistenceService.createJobExecution(jobExecution);

        Mockito.verify(jobEntity).setExecution(argument.capture());
        Mockito.verify(jobEntity).setStatus(argument1.capture());

        Mockito.verify(jobEntity, Mockito.times(1)).setStarted(Mockito.any());

        Assert.assertEquals(hostname, argument.getValue().getHostName());
        Assert.assertEquals(pid, argument.getValue().getProcessId());
        Assert.assertThat(argument.getValue().getCheckDelay(), Matchers.is(checkDelay));
        Assert.assertThat(argument.getValue().getTimeout(), Matchers.is(timeout));
        Assert.assertEquals(JOB_1_ID, argument.getValue().getId());

        // verify the method sets the status code of the Job to RUNNING
        Assert.assertEquals(JobStatus.RUNNING, argument1.getValue());
    }