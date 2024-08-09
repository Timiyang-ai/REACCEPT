@SuppressWarnings("unchecked")
    @Test(expected = GenieServerException.class)
    public void testSubmitJob() throws GenieException, IOException {

        final Set<CommandStatus> enumStatuses = EnumSet.noneOf(CommandStatus.class);
        enumStatuses.add(CommandStatus.ACTIVE);

        final String app1 = UUID.randomUUID().toString();
        final String app2 = UUID.randomUUID().toString();
        final String app3 = UUID.randomUUID().toString();
        final List<String> applications = Lists.newArrayList(app3, app1, app2);

        final String placeholder = UUID.randomUUID().toString();
        Mockito
            .when(this.applicationService.getApplication(app3))
            .thenReturn(
                new Application.Builder(placeholder, placeholder, placeholder, ApplicationStatus.ACTIVE)
                    .withId(app3)
                    .build()
            );
        Mockito
            .when(this.applicationService.getApplication(app1))
            .thenReturn(
                new Application.Builder(placeholder, placeholder, placeholder, ApplicationStatus.ACTIVE)
                    .withId(app1)
                    .build()
            );
        Mockito
            .when(this.applicationService.getApplication(app2))
            .thenReturn(
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
            .withApplications(applications)
            .build();

        final Cluster cluster = new Cluster.Builder(
            CLUSTER_NAME,
            USER,
            VERSION,
            ClusterStatus.UP
        )
            .withId(CLUSTER_ID)
            .build();


        final List<Cluster> clusterList = new ArrayList<>();
        clusterList.add(cluster);

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

        final List<Command> commandList = new ArrayList<>();
        commandList.add(command);

        Mockito.when(this.clusterService.chooseClusterForJobRequest(jobRequest)).thenReturn(clusterList);
        Mockito.when(this.clusterLoadBalancer.selectCluster(clusterList)).thenReturn(cluster);
        Mockito
            .when(this.clusterService.getCommandsForCluster(CLUSTER_ID, enumStatuses))
            .thenReturn(commandList);
        Mockito.doThrow(new IOException("something bad")).when(this.task2).executeTask(Mockito.anyMap());

        final ArgumentCaptor<String> jobId1 = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> clusterId = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> commandId = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<List<String>> applicationIds = ArgumentCaptor.forClass((Class) List.class);

        this.jobSubmitterService.submitJob(jobRequest);

        Mockito
            .verify(this.jobPersistenceService)
            .updateJobWithRuntimeEnvironment(
                jobId1.capture(),
                clusterId.capture(),
                commandId.capture(),
                applicationIds.capture()
            );

        Assert.assertThat(jobId1.getValue(), Matchers.is(JOB_1_ID));
        Assert.assertThat(clusterId.getValue(), Matchers.is(CLUSTER_ID));
        Assert.assertThat(commandId.getValue(), Matchers.is(COMMAND_ID));
        Assert.assertThat(applicationIds.getValue().get(0), Matchers.is(app3));
        Assert.assertThat(applicationIds.getValue().get(1), Matchers.is(app1));
        Assert.assertThat(applicationIds.getValue().get(2), Matchers.is(app2));
    }