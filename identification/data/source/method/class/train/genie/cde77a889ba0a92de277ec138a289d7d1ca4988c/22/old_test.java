@Test
    public void testCreateCommand() throws GenieException {
        final Command command = new Command(
                COMMAND_1_NAME,
                COMMAND_1_USER,
                COMMAND_1_VERSION,
                CommandStatus.ACTIVE,
                COMMAND_1_EXECUTABLE
        );
        final String id = UUID.randomUUID().toString();
        command.setId(id);
        final Command created = this.service.createCommand(command);
        Assert.assertNotNull(this.service.getCommand(id));
        Assert.assertEquals(id, created.getId());
        Assert.assertEquals(COMMAND_1_NAME, created.getName());
        Assert.assertEquals(COMMAND_1_USER, created.getUser());
        Assert.assertEquals(CommandStatus.ACTIVE, created.getStatus());
        Assert.assertEquals(COMMAND_1_EXECUTABLE, created.getExecutable());
        this.service.deleteCommand(id);
        try {
            this.service.getCommand(id);
            Assert.fail("Should have thrown exception");
        } catch (final GenieException ge) {
            Assert.assertEquals(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    ge.getErrorCode()
            );
        }
    }