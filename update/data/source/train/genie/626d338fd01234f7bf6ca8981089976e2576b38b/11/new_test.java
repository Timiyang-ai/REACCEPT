@SuppressWarnings("unchecked")
    @Test(expected = GenieServerException.class)
    public void testSubmitJob() throws GenieException, IOException {

        final Set<CommandStatus> enumStatuses = EnumSet.noneOf(CommandStatus.class);
        enumStatuses.add(CommandStatus.ACTIVE);

        final String placeholder = UUID.randomUUID().toString();
        final String app1 = UUID.randomUUID().toString();
        final String app2 = UUID.randomUUID().toString();
        final String app3 = UUID.randomUUID().toString();
        final List<Application> applications = Lists.newArrayList(
            new Application(
                app3,
                Instant.now(),
                Instant.now(),
                new ExecutionEnvironment(null, null, null),
                new ApplicationMetadata
                    .Builder(placeholder, placeholder, ApplicationStatus.ACTIVE)
                    .withVersion(placeholder)
                    .build()
            ),
            new Application(
                app1,
                Instant.now(),
                Instant.now(),
                new ExecutionEnvironment(null, null, null),
                new ApplicationMetadata
                    .Builder(placeholder, placeholder, ApplicationStatus.ACTIVE)
                    .withVersion(placeholder)
                    .build()
            ),
            new Application(
                app2,
                Instant.now(),
                Instant.now(),
                new ExecutionEnvironment(null, null, null),
                new ApplicationMetadata
                    .Builder(placeholder, placeholder, ApplicationStatus.ACTIVE)
                    .withVersion(placeholder)
                    .build()
            )
        );

        final JobRequest jobRequest = new JobRequest.Builder(
            JOB_1_NAME,
            USER,
            VERSION,
            Lists.newArrayList(),
            Sets.newHashSet()
        )
            .withId(JOB_1_ID)
            .withApplications(Lists.newArrayList(app3, app1, app2))
            .build();

        final Cluster cluster = new Cluster(
            CLUSTER_ID,
            Instant.now(),
            Instant.now(),
            new ExecutionEnvironment(null, null, null),
            new ClusterMetadata.Builder(CLUSTER_NAME, USER, ClusterStatus.UP).withVersion(VERSION).build()
        );

        final Command command = new Command(
            COMMAND_ID,
            Instant.now(),
            Instant.now(),
            new ExecutionEnvironment(null, null, null),
            new CommandMetadata.Builder(COMMAND_NAME, USER, CommandStatus.ACTIVE).withVersion(VERSION).build(),
            Lists.newArrayList("foo"),
            null,
            5000L
        );

        final int memory = 2438;

        Mockito.doThrow(new IOException("something bad")).when(this.task2).executeTask(Mockito.anyMap());

        try {
            this.jobSubmitterService.submitJob(jobRequest, cluster, command, applications, memory);
        } catch (Throwable t) {
            final File jobDirectory = new File(tmpFolder, JOB_1_ID);
            Assert.assertTrue(jobDirectory.exists());
            final File initFailureFile = new File(jobDirectory, JobConstants.GENIE_INIT_FAILURE_MESSAGE_FILE_NAME);
            Assert.assertTrue(initFailureFile.exists());
            Assert.assertTrue(initFailureFile.length() > 0);
            throw t;
        }
    }