@Test
    @DatabaseSetup("cluster/init.xml")
    @DatabaseTearDown(
            value = "cluster/init.xml",
            type = DatabaseOperation.DELETE_ALL)
    public void testUpdateCommandsForCluster() throws GenieException {
        final Command command1 = this.commandService.createCommand(
                new Command(
                        "name",
                        "user",
                        CommandStatus.ACTIVE,
                        "pig",
                        "23.1.0"
                )
        );
        final Command command2 = this.commandService.createCommand(
                new Command(
                        "name2",
                        "user2",
                        CommandStatus.INACTIVE,
                        "pig2",
                        "23.1.1"
                )
        );
        final List<Command> newCommands = new ArrayList<Command>();
        newCommands.add(command1);
        newCommands.add(command2);
        Assert.assertEquals(
                3,
                this.service.getCommandsForCluster(CLUSTER_1_ID).size()
        );
        final List<Command> commands
                = this.service.updateCommandsForCluster(
                        CLUSTER_1_ID,
                        newCommands
                );
        Assert.assertEquals(
                2,
                commands.size()
        );
    }