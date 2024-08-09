@Test
    public void testCreateJob() throws GenieException {
        final int cpu = 1;
        final int mem = 1;
        final String email = "name@domain.com";
        final String setupFile = "setupFilePath";
        final String group = "group";
        final String description = "job description";
        final Set<String> tags = new HashSet<>();
        tags.add("foo");
        tags.add("bar");

        final JobRequest jobRequest = new JobRequest.Builder(
            JOB_1_NAME,
            JOB_1_USER,
            JOB_1_VERSION,
            JOB_1_COMMAND_ARGS,
            Lists.newArrayList(),
            Sets.newHashSet()

        ).withId(JOB_1_ID)
            .withDescription(description)
            .withCpu(cpu)
            .withMemory(mem)
            .withEmail(email)
            .withSetupFile(setupFile)
            .withGroup(group)
            .withTags(tags)
            .build();

        final String clientHost = UUID.randomUUID().toString();
        final String userAgent = UUID.randomUUID().toString();
        final int numAttachments = 380;
        final long totalSizeOfAttachments = 830803L;
        final JobMetadata metadata = new JobMetadata
            .Builder()
            .withClientHost(clientHost)
            .withUserAgent(userAgent)
            .withNumAttachments(numAttachments)
            .withTotalSizeOfAttachments(totalSizeOfAttachments)
            .build();

        final Job job = new Job.Builder(JOB_1_NAME, JOB_1_USER, JOB_1_VERSION, JOB_1_COMMAND_ARGS)
            .withStatus(JobStatus.INIT)
            .withStatusMsg("Job is initializing")
            .build();

        final JobExecution execution = new JobExecution.Builder(UUID.randomUUID().toString()).build();

        final ArgumentCaptor<JobRequestEntity> argument = ArgumentCaptor.forClass(JobRequestEntity.class);
        this.jobPersistenceService.createJob(jobRequest, metadata, job, execution);
        Mockito.verify(this.jobRequestRepo).save(argument.capture());
        // Make sure id supplied is used to create the JobRequest
        Assert.assertEquals(JOB_1_ID, argument.getValue().getId());
        Assert.assertEquals(JOB_1_USER, argument.getValue().getUser());
        Assert.assertEquals(JOB_1_VERSION, argument.getValue().getVersion());
        Assert.assertEquals(JOB_1_NAME, argument.getValue().getName());
        final int actualCpu = argument.getValue().getCpu().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualCpu, Matchers.is(cpu));
        final int actualMemory = argument.getValue().getMemory().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualMemory, Matchers.is(mem));
        final String actualEmail = argument.getValue().getEmail().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualEmail, Matchers.is(email));
        final String actualSetupFile = argument.getValue().getSetupFile().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualSetupFile, Matchers.is(setupFile));
        final String actualGroup = argument.getValue().getGroup().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualGroup, Matchers.is(group));
        Assert.assertEquals(tags, argument.getValue().getTags());
        final String actualDescription
            = argument.getValue().getDescription().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualDescription, Matchers.is(description));
        Assert.assertThat(argument.getValue().getApplicationsAsList(), Matchers.empty());
        Assert.assertThat(argument.getValue().getJob(), Matchers.notNullValue());
        Assert.assertThat(argument.getValue().getJob().getExecution(), Matchers.notNullValue());
        Assert.assertThat(argument.getValue().getJobMetadata(), Matchers.notNullValue());
    }