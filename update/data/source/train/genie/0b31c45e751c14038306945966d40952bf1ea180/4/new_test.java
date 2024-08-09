@Test
    public void testStringToCommandCriteria() throws GeniePreconditionException {
        Assert.assertEquals(COMMAND_CRITERIA,
                this.job.stringToCommandCriteria(
                        EXPECTED_COMMAND_CRITERIA_STRING));
    }