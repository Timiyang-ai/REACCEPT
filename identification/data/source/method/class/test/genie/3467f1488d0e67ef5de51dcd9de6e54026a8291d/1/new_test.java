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
            new Application.Builder(placeholder, placeholder, placeholder, ApplicationStatus.ACTIVE)
                .withId(app3)
                .build(),
            new Application.Builder(placeholder, placeholder, placeholder, ApplicationStatus.ACTIVE)
                .withId(app1)
                .build(),
            new Application.Builder(placeholder, placeholder, placeholder, ApplicationStatus.ACTIVE)
                .withId(app2)
                .build()
        );

        final JobRequest jobRequest = new JobRequest.Builder(
            JOB_1_NAME,
            USER,
            VERSION,
            null,
            null,
            null
        )
            .withId(JOB_1_ID)
            .withApplications(Lists.newArrayList(app3, app1, app2))
            .build();

        final Cluster cluster = new Cluster.Builder(
            CLUSTER_NAME,
            USER,
            VERSION,
            ClusterStatus.UP
        )
            .withId(CLUSTER_ID)
            .build();

        final Command command = new Command.Builder(
            COMMAND_NAME,
            USER,
            VERSION,
            CommandStatus.ACTIVE,
            "foo",
            5000L
        )
            .withId(COMMAND_ID)
            .build();

        final int memory = 2438;

        Mockito.doThrow(new IOException("something bad")).when(this.task2).executeTask(Mockito.anyMap());

        this.jobSubmitterService.submitJob(jobRequest, cluster, command, applications, memory);
    }