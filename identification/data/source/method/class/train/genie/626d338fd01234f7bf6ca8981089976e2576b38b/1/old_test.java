@Test
    public void testUpdateCommand() throws GenieException {
        final Command command = this.service.getCommand(COMMAND_1_ID);
        Assert.assertEquals(COMMAND_1_USER, command.getUser());
        Assert.assertEquals(CommandStatus.ACTIVE, command.getStatus());
        Assert.assertEquals(5, command.getTags().size());
        Assert.assertFalse(command.getMemory().isPresent());
        final Set<String> tags = Sets.newHashSet("yarn", "hadoop");
        tags.addAll(command.getTags());

        final int memory = 1_024;
        final Command.Builder updateCommand = new Command.Builder(
            command.getName(),
            COMMAND_2_USER,
            command.getVersion(),
            CommandStatus.INACTIVE,
            command.getExecutable(),
            command.getCheckDelay()
        )
            .withId(command.getId().orElseThrow(IllegalArgumentException::new))
            .withCreated(command.getCreated().orElseThrow(IllegalArgumentException::new))
            .withUpdated(command.getUpdated().orElseThrow(IllegalArgumentException::new))
            .withTags(tags)
            .withConfigs(command.getConfigs())
            .withDependencies(command.getDependencies())
            .withMemory(memory);

        command.getDescription().ifPresent(updateCommand::withDescription);
        command.getSetupFile().ifPresent(updateCommand::withSetupFile);

        this.service.updateCommand(COMMAND_1_ID, updateCommand.build());

        final Command updated = this.service.getCommand(COMMAND_1_ID);
        Assert.assertEquals(COMMAND_2_USER, updated.getUser());
        Assert.assertEquals(CommandStatus.INACTIVE, updated.getStatus());
        Assert.assertEquals(7, updated.getTags().size());
        Assert.assertThat(updated.getMemory().orElse(memory + 1), Matchers.is(memory));
    }