@Test
    public void testCreateCommand() throws GenieException {
        final String id = UUID.randomUUID().toString();
        final CommandRequest command = new CommandRequest.Builder(
            new CommandMetadata.Builder(
                COMMAND_1_NAME,
                COMMAND_1_USER,
                CommandStatus.ACTIVE
            )
                .withVersion(COMMAND_1_VERSION)
                .build(),
            COMMAND_1_EXECUTABLE
        )
            .withRequestedId(id)
            .withCheckDelay(COMMAND_1_CHECK_DELAY)
            .build();
        final String createdId = this.service.createCommand(command);
        Assert.assertThat(createdId, Matchers.is(id));
        final Command created = this.service.getCommand(id);
        Assert.assertNotNull(this.service.getCommand(id));
        Assert.assertEquals(id, created.getId());
        Assert.assertEquals(COMMAND_1_NAME, created.getMetadata().getName());
        Assert.assertEquals(COMMAND_1_USER, created.getMetadata().getUser());
        Assert.assertEquals(CommandStatus.ACTIVE, created.getMetadata().getStatus());
        Assert.assertEquals(COMMAND_1_EXECUTABLE, created.getExecutable());
        Assert.assertThat(COMMAND_1_CHECK_DELAY, Matchers.is(created.getCheckDelay()));
        Assert.assertFalse(created.getMemory().isPresent());
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