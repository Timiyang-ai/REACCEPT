@Test
    public void testCreateCommand() throws GenieException {
        final String id = UUID.randomUUID().toString();
        final Command command = new Command.Builder(
            COMMAND_1_NAME,
            COMMAND_1_USER,
            COMMAND_1_VERSION,
            CommandStatus.ACTIVE,
            COMMAND_1_EXECUTABLE,
            COMMAND_1_CHECK_DELAY
        )
            .withId(id)
            .build();
        final String createdId = this.service.createCommand(command);
        Assert.assertThat(createdId, Matchers.is(id));
        final Command created = this.service.getCommand(id);
        Assert.assertNotNull(this.service.getCommand(id));
        Assert.assertEquals(id, created.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(COMMAND_1_NAME, created.getName());
        Assert.assertEquals(COMMAND_1_USER, created.getUser());
        Assert.assertEquals(CommandStatus.ACTIVE, created.getStatus());
        Assert.assertEquals(COMMAND_1_EXECUTABLE, created.getExecutable());
        Assert.assertThat(COMMAND_1_CHECK_DELAY, Matchers.is(created.getCheckDelay()));
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