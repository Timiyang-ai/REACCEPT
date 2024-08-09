@Test
    public void testPatchCommand() throws GenieException, IOException {
        final Command getCommand = this.service.getCommand(COMMAND_1_ID);
        Assert.assertThat(getCommand.getMetadata().getName(), Matchers.is(COMMAND_1_NAME));
        final Instant updateTime = getCommand.getUpdated();

        final String patchString
            = "[{ \"op\": \"replace\", \"path\": \"/metadata/name\", \"value\": \"" + COMMAND_2_NAME + "\" }]";
        final JsonPatch patch = JsonPatch.fromJson(GenieObjectMapper.getMapper().readTree(patchString));

        this.service.patchCommand(COMMAND_1_ID, patch);

        final Command updated = this.service.getCommand(COMMAND_1_ID);
        Assert.assertNotEquals(updated.getUpdated(), Matchers.is(updateTime));
        Assert.assertThat(updated.getMetadata().getName(), Matchers.is(COMMAND_2_NAME));
    }