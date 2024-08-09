@Test
    public void testUpdateCommandsForCluster() throws GenieException {
        final Command command1 = this.commandService.createCommand(
                new Command(
                        "name",
                        "user",
                        "23.1.0",
                        CommandStatus.ACTIVE,
                        "pig"
                )
        );
        final Command command2 = this.commandService.createCommand(
                new Command(
                        "name2",
                        "user2",
                        "23.1.1",
                        CommandStatus.INACTIVE,
                        "pig2"
                )
        );
        final List<Command> newCommands = new ArrayList<>();
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