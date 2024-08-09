@Test
    public void testStringToCommandCriteria() throws GenieException {
        Assert.assertEquals(COMMAND_CRITERIA,
                this.job.stringToCommandCriteria(
                        EXPECTED_COMMAND_CRITERIA_STRING));
    }