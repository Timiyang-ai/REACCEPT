@Test
    public void testUpdateCommand() throws Exception {
        final Command command1 = constructCommandDTO(null);
        commandClient.createCommand(command1);

        final Command command2 = commandClient.getCommand(command1.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(command2.getName(), command1.getName());

        final Command command3 = new
            Command.Builder("newname", "newuser", "new version", CommandStatus.ACTIVE, "exec", 1000)
            .withId(command1.getId().orElseThrow(IllegalArgumentException::new))
            .build();

        commandClient.updateCommand(command1.getId().orElseThrow(IllegalArgumentException::new), command3);

        final Command command4 = commandClient.getCommand(command1.getId().orElseThrow(IllegalArgumentException::new));

        Assert.assertEquals("newname", command4.getName());
        Assert.assertEquals("newuser", command4.getUser());
        Assert.assertEquals("new version", command4.getVersion());
        Assert.assertEquals(CommandStatus.ACTIVE, command4.getStatus());
        Assert.assertFalse(command4.getSetupFile().isPresent());
        Assert.assertFalse(command4.getDescription().isPresent());
        Assert.assertEquals(Collections.emptySet(), command4.getConfigs());
        Assert.assertEquals(command4.getTags().contains("foo"), false);
    }