@Test
    public void testGetCommand() throws GenieException {
        final Command command1 = this.service.getCommand(COMMAND_1_ID);
        Assert.assertEquals(COMMAND_1_ID, command1.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(COMMAND_1_NAME, command1.getName());
        Assert.assertEquals(COMMAND_1_USER, command1.getUser());
        Assert.assertEquals(COMMAND_1_VERSION, command1.getVersion());
        Assert.assertEquals(COMMAND_1_STATUS, command1.getStatus());
        Assert.assertEquals(COMMAND_1_EXECUTABLE, command1.getExecutable());
        Assert.assertEquals(5, command1.getTags().size());
        Assert.assertEquals(2, command1.getConfigs().size());
        Assert.assertEquals(0, command1.getDependencies().size());

        final Command command2 = this.service.getCommand(COMMAND_2_ID);
        Assert.assertEquals(COMMAND_2_ID, command2.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(COMMAND_2_NAME, command2.getName());
        Assert.assertEquals(COMMAND_2_USER, command2.getUser());
        Assert.assertEquals(COMMAND_2_VERSION, command2.getVersion());
        Assert.assertEquals(COMMAND_2_STATUS, command2.getStatus());
        Assert.assertEquals(COMMAND_2_EXECUTABLE, command2.getExecutable());
        Assert.assertEquals(4, command2.getTags().size());
        Assert.assertEquals(1, command2.getConfigs().size());
        Assert.assertEquals(1, command2.getDependencies().size());

        final Command command3 = this.service.getCommand(COMMAND_3_ID);
        Assert.assertEquals(COMMAND_3_ID, command3.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(COMMAND_3_NAME, command3.getName());
        Assert.assertEquals(COMMAND_3_USER, command3.getUser());
        Assert.assertEquals(COMMAND_3_VERSION, command3.getVersion());
        Assert.assertEquals(COMMAND_3_STATUS, command3.getStatus());
        Assert.assertEquals(COMMAND_3_EXECUTABLE, command3.getExecutable());
        Assert.assertEquals(5, command3.getTags().size());
        Assert.assertEquals(1, command3.getConfigs().size());
        Assert.assertEquals(2, command3.getDependencies().size());
    }