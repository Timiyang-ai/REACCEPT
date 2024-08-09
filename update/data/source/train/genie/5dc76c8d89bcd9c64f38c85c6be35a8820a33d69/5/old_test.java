@Test
    public void testCoordinateJob() throws GenieException {
        final int cpu = 1;
        final int mem = 1;
        final String email = "name@domain.com";
        final String setupFile = "setupFilePath";
        final String group = "group";
        final String description = "job description";
        final Set<String> tags = new HashSet<>();
        final String clientHost = "localhost";
        tags.add("foo");
        tags.add("bar");

        final JobRequest jobRequest = new JobRequest.Builder(
            JOB_1_NAME,
            JOB_1_USER,
            JOB_1_VERSION,
            null,
            null,
            null

        ).withId(JOB_1_ID)
            .withDescription(description)
            .withCpu(cpu)
            .withMemory(mem)
            .withEmail(email)
            .withSetupFile(setupFile)
            .withGroup(group)
            .withTags(tags)
            .withDisableLogArchival(true)
            .build();

        final JobRequestMetadata metadata = new JobRequestMetadata
            .Builder()
            .withClientHost(clientHost)
            .withUserAgent(UUID.randomUUID().toString())
            .withNumAttachments(2)
            .withTotalSizeOfAttachments(28080L)
            .build();

        Mockito.when(this.jobPersistenceService.createJobRequest(jobRequest, metadata)).thenReturn(jobRequest);
        final Future<?> task = Mockito.mock(Future.class);
        Mockito.doReturn(task).when(this.taskExecutor).submit(Mockito.any(JobLauncher.class));

        this.jobCoordinatorService.coordinateJob(jobRequest, metadata);

        Mockito.verify(this.taskExecutor, Mockito.times(1)).submit(Mockito.any(JobLauncher.class));
        Mockito.verify(this.eventPublisher, Mockito.times(1)).publishEvent(Mockito.any(JobScheduledEvent.class));

        final ArgumentCaptor<Job> jobArgumentCaptor = ArgumentCaptor.forClass(Job.class);
        final ArgumentCaptor<JobExecution> jobExecutionArgumentCaptor = ArgumentCaptor.forClass(JobExecution.class);
        Mockito.verify(this.jobPersistenceService).createJobAndJobExecution(
            jobArgumentCaptor.capture(),
            jobExecutionArgumentCaptor.capture()
        );

        Assert.assertEquals(JOB_1_ID, jobArgumentCaptor.getValue().getId());
        Assert.assertEquals(JOB_1_NAME, jobArgumentCaptor.getValue().getName());
        Assert.assertEquals(JOB_1_USER, jobArgumentCaptor.getValue().getUser());
        Assert.assertEquals(JOB_1_VERSION, jobArgumentCaptor.getValue().getVersion());
        Assert.assertEquals(JobStatus.INIT, jobArgumentCaptor.getValue().getStatus());
        Assert.assertEquals(description, jobArgumentCaptor.getValue().getDescription());
    }