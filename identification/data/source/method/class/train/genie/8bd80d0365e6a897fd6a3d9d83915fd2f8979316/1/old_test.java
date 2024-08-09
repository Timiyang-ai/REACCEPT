@Test(expected = GenieServerException.class)
    public void testSubmitJob() throws GenieException {

        final Set<CommandStatus> enumStatuses = EnumSet.noneOf(CommandStatus.class);
        enumStatuses.add(CommandStatus.ACTIVE);

        final JobRequest jobRequest = new JobRequest.Builder(
            JOB_1_NAME,
            USER,
            VERSION,
            null,
            null,
            null
        ).
            withId(JOB_1_ID)
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

        Mockito.when(this.clusterService.chooseClusterForJobRequest(Mockito.eq(jobRequest))).thenReturn(clusterList);
        Mockito.when(this.clusterLoadBalancer.selectCluster(Mockito.eq(clusterList))).thenReturn(cluster);
        Mockito
            .when(this.clusterService.getCommandsForCluster(Mockito.eq(CLUSTER_ID), Mockito.eq(enumStatuses)))
            .thenReturn(commandList);
        //Mockito.when(this.wfExecutor.executeWorkflow(Mockito.any(), Mockito.anyMap())).thenReturn(true);

        final ArgumentCaptor<String> jobId1 = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> jobId2 = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> clusterId = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> commandId = ArgumentCaptor.forClass(String.class);

        this.jobSubmitterService.submitJob(jobRequest);

        Mockito.verify(this.jobPersistenceService).updateClusterForJob(jobId1.capture(), clusterId.capture());
        Mockito.verify(this.jobPersistenceService).updateCommandForJob(jobId2.capture(), commandId.capture());

        Assert.assertEquals(JOB_1_ID, jobId1.getValue());
        Assert.assertEquals(JOB_1_ID, jobId2.getValue());
        Assert.assertEquals(CLUSTER_ID, clusterId.getValue());
        Assert.assertEquals(COMMAND_ID, commandId.getValue());
    }