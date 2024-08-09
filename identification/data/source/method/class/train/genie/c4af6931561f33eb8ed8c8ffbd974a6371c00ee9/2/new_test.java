@Test
    public void testSetApplicationsForCommand() throws GenieException {
        final Command command2 = this.service.getCommand(COMMAND_2_ID);
        Assert.assertTrue(command2.getApplications().isEmpty());

        final Set<String> appIds = new HashSet<>();
        appIds.add(APP_1_ID);
        final List<Command> preCommands = this.appService.getCommandsForApplication(APP_1_ID, null);
        Assert.assertEquals(1, preCommands.size());
        Assert.assertEquals(1, preCommands
                        .stream()
                        .filter(command -> COMMAND_1_ID.equals(command.getId()))
                        .count()
        );

        this.service.setApplicationsForCommand(COMMAND_2_ID, appIds);

        final List<Command> savedCommands = this.appService.getCommandsForApplication(APP_1_ID, null);
        Assert.assertEquals(2, savedCommands.size());
        Assert.assertEquals(
                1,
                this.service.getApplicationsForCommand(COMMAND_2_ID)
                        .stream()
                        .filter(application -> APP_1_ID.equals(application.getId()))
                        .count()
        );
    }