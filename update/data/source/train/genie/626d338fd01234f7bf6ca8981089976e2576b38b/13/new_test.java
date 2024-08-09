@Test
    public void testUpdateCommand() throws GenieException {
        final Command command = this.service.getCommand(COMMAND_1_ID);
        Assert.assertEquals(COMMAND_1_USER, command.getMetadata().getUser());
        Assert.assertEquals(CommandStatus.ACTIVE, command.getMetadata().getStatus());
        Assert.assertEquals(3, command.getMetadata().getTags().size());
        Assert.assertFalse(command.getMemory().isPresent());
        final Set<String> tags = Sets.newHashSet("yarn", "hadoop");
        tags.addAll(command.getMetadata().getTags());

        final int memory = 1_024;
        final Command updateCommand = new Command(
            command.getId(),
            command.getCreated(),
            command.getUpdated(),
            command.getResources(),
            new CommandMetadata.Builder(
                command.getMetadata().getName(),
                COMMAND_2_USER,
                CommandStatus.INACTIVE
            )
                .withVersion(command.getMetadata().getVersion().orElse(null))
                .withMetadata(command.getMetadata().getMetadata().orElse(null))
                .withDescription(command.getMetadata().getDescription().orElse(null))
                .withTags(tags)
                .build(),
            command.getExecutable(),
            memory,
            command.getCheckDelay()
        );

        this.service.updateCommand(COMMAND_1_ID, updateCommand);

        final Command updated = this.service.getCommand(COMMAND_1_ID);
        Assert.assertEquals(COMMAND_2_USER, updated.getMetadata().getUser());
        Assert.assertEquals(CommandStatus.INACTIVE, updated.getMetadata().getStatus());
        Assert.assertEquals(5, updated.getMetadata().getTags().size());
        Assert.assertThat(updated.getMemory().orElse(memory + 1), Matchers.is(memory));
    }