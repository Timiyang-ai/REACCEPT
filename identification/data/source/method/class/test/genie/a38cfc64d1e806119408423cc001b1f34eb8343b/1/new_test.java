@Test
    public void testGetCommandsForCluster() throws GenieException {
        final List<Command> commands
                = this.service.getCommandsForCluster(CLUSTER_1_ID, null);
        Assert.assertEquals(3, commands.size());
        Assert.assertEquals(COMMAND_1_ID, commands.get(0).getId());
        Assert.assertEquals(COMMAND_3_ID, commands.get(1).getId());
        Assert.assertEquals(COMMAND_2_ID, commands.get(2).getId());
    }