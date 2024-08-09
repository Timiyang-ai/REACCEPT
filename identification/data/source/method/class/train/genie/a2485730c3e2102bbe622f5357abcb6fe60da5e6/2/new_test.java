@Test
    public void testAddApplicationsForCommand() throws GenieException {
        Assert.assertTrue(this.service.getApplicationsForCommand(COMMAND_2_ID).isEmpty());

        final List<String> appIds = new ArrayList<>();
        appIds.add(APP_1_ID);
        final Set<Command> preCommands = this.appService.getCommandsForApplication(APP_1_ID, null);
        Assert.assertEquals(1, preCommands.size());
        Assert.assertEquals(1, preCommands
            .stream()
            .filter(command -> COMMAND_1_ID.equals(command.getId()))
            .count()
        );

        this.service.addApplicationsForCommand(COMMAND_2_ID, appIds);

        final Set<Command> savedCommands = this.appService.getCommandsForApplication(APP_1_ID, null);
        Assert.assertEquals(2, savedCommands.size());
        Assert.assertThat(this.service.getApplicationsForCommand(COMMAND_2_ID).get(0).getId(), Matchers.is(APP_1_ID));
    }