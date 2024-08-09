@Test
    public void testCreateJob() throws GenieException {
        final String name = UUID.randomUUID().toString();
        final String user = UUID.randomUUID().toString();
        final String version = UUID.randomUUID().toString();
        final String commandArgs = UUID.randomUUID().toString();
        final List<ClusterCriteria> clusterCriterias = new ArrayList<>();
        final ClusterCriteria criteria1 = new ClusterCriteria();
        final Set<String> tags1 = new HashSet<>();
        tags1.add(UUID.randomUUID().toString());
        tags1.add(UUID.randomUUID().toString());
        criteria1.setTags(tags1);
        clusterCriterias.add(criteria1);
        final ClusterCriteria criteria2 = new ClusterCriteria();
        final Set<String> tags2 = new HashSet<>();
        tags2.add(UUID.randomUUID().toString());
        tags2.add(UUID.randomUUID().toString());
        criteria2.setTags(tags2);
        clusterCriterias.add(criteria2);

        final Set<String> commandCriteria = new HashSet<>();
        commandCriteria.add(UUID.randomUUID().toString());
        commandCriteria.add(UUID.randomUUID().toString());

        final Job created = this.service.createJob(
                new Job(
                        user,
                        name,
                        commandArgs,
                        commandCriteria,
                        clusterCriterias,
                        version
                )
        );

        final Job job = this.service.getJob(created.getId());
        Assert.assertNotNull(job.getId());
        Assert.assertEquals(name, job.getName());
        Assert.assertEquals(user, job.getUser());
        Assert.assertEquals(version, job.getVersion());
        Assert.assertEquals(commandArgs, job.getCommandArgs());
        Assert.assertEquals(clusterCriterias.size(), job.getClusterCriterias().size());
        Assert.assertEquals(commandCriteria.size(), job.getCommandCriteria().size());
        Assert.assertEquals(commandCriteria.size(), job.getCommandCriteriaString().split(",").length);
        Assert.assertEquals(JobStatus.INIT, job.getStatus());
        Assert.assertNotNull(job.getHostName());
        Assert.assertNotNull(job.getOutputURI());
        Assert.assertNotNull(job.getKillURI());
    }