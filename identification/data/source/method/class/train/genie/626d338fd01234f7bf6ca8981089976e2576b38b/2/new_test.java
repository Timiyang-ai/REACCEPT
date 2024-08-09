@Test
    public void testGetCommand() throws GenieException {
        final Command command1 = this.service.getCommand(COMMAND_1_ID);
        Assert.assertEquals(COMMAND_1_ID, command1.getId());
        Assert.assertEquals(COMMAND_1_NAME, command1.getMetadata().getName());
        Assert.assertEquals(COMMAND_1_USER, command1.getMetadata().getUser());
        Assert.assertEquals(COMMAND_1_VERSION, command1.getMetadata().getVersion().orElse(null));
        Assert.assertEquals(COMMAND_1_STATUS, command1.getMetadata().getStatus());
        Assert.assertEquals(COMMAND_1_EXECUTABLE, command1.getExecutable());
        Assert.assertEquals(3, command1.getMetadata().getTags().size());
        Assert.assertEquals(2, command1.getResources().getConfigs().size());
        Assert.assertEquals(0, command1.getResources().getDependencies().size());

        final Command command2 = this.service.getCommand(COMMAND_2_ID);
        Assert.assertEquals(COMMAND_2_ID, command2.getId());
        Assert.assertEquals(COMMAND_2_NAME, command2.getMetadata().getName());
        Assert.assertEquals(COMMAND_2_USER, command2.getMetadata().getUser());
        Assert.assertEquals(COMMAND_2_VERSION, command2.getMetadata().getVersion().orElse(null));
        Assert.assertEquals(COMMAND_2_STATUS, command2.getMetadata().getStatus());
        Assert.assertEquals(COMMAND_2_EXECUTABLE, command2.getExecutable());
        Assert.assertEquals(2, command2.getMetadata().getTags().size());
        Assert.assertEquals(1, command2.getResources().getConfigs().size());
        Assert.assertEquals(1, command2.getResources().getDependencies().size());

        final Command command3 = this.service.getCommand(COMMAND_3_ID);
        Assert.assertEquals(COMMAND_3_ID, command3.getId());
        Assert.assertEquals(COMMAND_3_NAME, command3.getMetadata().getName());
        Assert.assertEquals(COMMAND_3_USER, command3.getMetadata().getUser());
        Assert.assertEquals(COMMAND_3_VERSION, command3.getMetadata().getVersion().orElse(null));
        Assert.assertEquals(COMMAND_3_STATUS, command3.getMetadata().getStatus());
        Assert.assertEquals(COMMAND_3_EXECUTABLE, command3.getExecutable());
        Assert.assertEquals(3, command3.getMetadata().getTags().size());
        Assert.assertEquals(1, command3.getResources().getConfigs().size());
        Assert.assertEquals(2, command3.getResources().getDependencies().size());
    }