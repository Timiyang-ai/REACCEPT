@Test
    public void testCreateJob() throws GenieException {
        final int cpu = 1;
        final int mem = 1;
        final String email = "name@domain.com";
        final String setupFile = "setupFilePath";
        final String group = "group";
        final String description = "job description";
        final Set<String> tags = Sets.newHashSet("foo", "bar");

        final JobRequest jobRequest = new JobRequest.Builder(
            JOB_1_NAME,
            JOB_1_USER,
            JOB_1_VERSION,
            Lists.newArrayList(),
            Sets.newHashSet()
        ).withId(JOB_1_ID)
            .withDescription(description)
            .withCommandArgs(JOB_1_COMMAND_ARGS)
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

        final Job job = new Job.Builder(JOB_1_NAME, JOB_1_USER, JOB_1_VERSION)
            .withStatus(JobStatus.INIT)
            .withStatusMsg("Job is initializing")
            .withCommandArgs(JOB_1_COMMAND_ARGS)
            .build();

        final JobExecution execution = new JobExecution.Builder(UUID.randomUUID().toString()).build();

        final TagEntity fooTag = new TagEntity();
        fooTag.setTag("foo");
        Mockito.when(this.tagRepository.findByTag("foo")).thenReturn(Optional.of(fooTag));
        final TagEntity barTag = new TagEntity();
        barTag.setTag("bar");
        Mockito.when(this.tagRepository.findByTag("bar")).thenReturn(Optional.of(barTag));
        final FileEntity setupFileEntity = new FileEntity();
        setupFileEntity.setFile(setupFile);
        Mockito.when(this.fileRepository.findByFile(setupFile)).thenReturn(Optional.of(setupFileEntity));

        final ArgumentCaptor<JobEntity> argument = ArgumentCaptor.forClass(JobEntity.class);
        this.jobPersistenceService.createJob(jobRequest, metadata, job, execution);
        Mockito.verify(this.jobRepository).save(argument.capture());
        // Make sure id supplied is used to create the JobRequest
        Assert.assertEquals(JOB_1_ID, argument.getValue().getUniqueId());
        Assert.assertEquals(JOB_1_USER, argument.getValue().getUser());
        Assert.assertEquals(JOB_1_VERSION, argument.getValue().getVersion());
        Assert.assertEquals(JOB_1_NAME, argument.getValue().getName());
        final int actualCpu = argument.getValue().getCpuRequested().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualCpu, Matchers.is(cpu));
        final int actualMemory = argument.getValue().getMemoryRequested().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualMemory, Matchers.is(mem));
        final String actualEmail = argument.getValue().getEmail().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualEmail, Matchers.is(email));
        final String actualSetupFile
            = argument.getValue().getSetupFile().orElseThrow(IllegalArgumentException::new).getFile();
        Assert.assertThat(actualSetupFile, Matchers.is(setupFile));
        final String actualGroup = argument.getValue().getGenieUserGroup().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualGroup, Matchers.is(group));
        Assert.assertEquals(
            tags,
            argument.getValue().getTags().stream().map(TagEntity::getTag).collect(Collectors.toSet())
        );
        final String actualDescription
            = argument.getValue().getDescription().orElseThrow(IllegalArgumentException::new);
        Assert.assertThat(actualDescription, Matchers.is(description));
        Assert.assertThat(argument.getValue().getApplicationsRequested(), Matchers.empty());
    }