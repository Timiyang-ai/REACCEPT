@Test
    public void testGetApplicationsForCommand() throws GenieException {
        Assert.assertEquals(1, this.service.getApplicationsForCommand(COMMAND_1_ID)
            .stream()
            .filter(application -> APP_1_ID.equals(application.getId().orElseThrow(IllegalArgumentException::new)))
            .count()
        );
    }