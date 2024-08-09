@Test
    public void testSetApplicationsForCommand() throws GenieException {
        Assert.assertTrue(this.service.getApplicationsForCommand(COMMAND_2_ID).isEmpty());

        final List<String> appIds = Lists.newArrayList(APP_1_ID);
        final Set<Command> preCommands = this.appService.getCommandsForApplication(APP_1_ID, null);
        Assert.assertEquals(1, preCommands.size());
        Assert.assertEquals(1, preCommands
            .stream()
            .filter(command -> COMMAND_1_ID.equals(command.getId().orElseThrow(IllegalArgumentException::new)))
            .count()
        );

        this.service.setApplicationsForCommand(COMMAND_2_ID, appIds);

        final Set<Command> savedCommands = this.appService.getCommandsForApplication(APP_1_ID, null);
        Assert.assertEquals(2, savedCommands.size());
        Assert.assertEquals(
            1,
            this.service.getApplicationsForCommand(COMMAND_2_ID)
                .stream()
                .filter(application -> APP_1_ID.equals(application.getId().orElseThrow(IllegalArgumentException::new)))
                .count()
        );
    }