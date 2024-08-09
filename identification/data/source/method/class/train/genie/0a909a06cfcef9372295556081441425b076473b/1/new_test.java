@Test
    public void testUpdateCommand() throws GenieException {
        final Command command = this.service.getCommand(COMMAND_1_ID);
        Assert.assertEquals(COMMAND_1_USER, command.getUser());
        Assert.assertEquals(CommandStatus.ACTIVE, command.getStatus());
        Assert.assertEquals(5, command.getTags().size());
        final Set<String> tags = new HashSet<>(command.getTags());
        tags.add("yarn");
        tags.add("hadoop");

        final Command updateCommand = new Command.Builder(
                command.getName(),
                COMMAND_2_USER,
                command.getVersion(),
                CommandStatus.INACTIVE,
                command.getExecutable()
        )
                .withId(command.getId())
                .withCreated(command.getCreated())
                .withUpdated(command.getUpdated())
                .withDescription(command.getDescription())
                .withTags(tags)
                .withConfigs(command.getConfigs())
                .withSetupFile(command.getSetupFile())
                .build();

        this.service.updateCommand(COMMAND_1_ID, updateCommand);

        final Command updated = this.service.getCommand(COMMAND_1_ID);
        Assert.assertEquals(COMMAND_2_USER, updated.getUser());
        Assert.assertEquals(CommandStatus.INACTIVE, updated.getStatus());
        Assert.assertEquals(7, updated.getTags().size());
    }