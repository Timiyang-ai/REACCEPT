@Test
    public void testUpdateJobStatus() throws GenieException {
        final String id = UUID.randomUUID().toString();
        final JobEntity jobEntity = new JobEntity();
        final ArgumentCaptor<JobEntity> argument = ArgumentCaptor.forClass(JobEntity.class);

        Mockito.when(this.jobRepo.findOne(Mockito.eq(id))).thenReturn(jobEntity);
        this.jobPersistenceService.updateJobStatus(id, JobStatus.RUNNING, JOB_1_STATUS_MSG);

        Mockito.verify(jobRepo).save(argument.capture());

        Assert.assertEquals(JobStatus.RUNNING, argument.getValue().getStatus());
        Assert.assertEquals(JOB_1_STATUS_MSG, argument.getValue().getStatusMsg());
        Assert.assertEquals(new Date(0), argument.getValue().getFinished());
    }