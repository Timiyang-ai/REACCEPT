@Test
    public void testUpdateCommand() throws GenieException {
        final Command updateCommand = this.service.getCommand(COMMAND_1_ID);
        Assert.assertEquals(COMMAND_1_USER, updateCommand.getUser());
        Assert.assertEquals(CommandStatus.ACTIVE, updateCommand.getStatus());
        Assert.assertEquals(5, updateCommand.getTags().size());

        updateCommand.setStatus(CommandStatus.INACTIVE);
        updateCommand.setUser(COMMAND_2_USER);
        final Set<String> tags = updateCommand.getTags();
        tags.add("yarn");
        tags.add("hadoop");
        this.service.updateCommand(COMMAND_1_ID, updateCommand);

        final Command updated = this.service.getCommand(COMMAND_1_ID);
        Assert.assertEquals(COMMAND_2_USER, updated.getUser());
        Assert.assertEquals(CommandStatus.INACTIVE, updated.getStatus());
        Assert.assertEquals(7, updated.getTags().size());
    }