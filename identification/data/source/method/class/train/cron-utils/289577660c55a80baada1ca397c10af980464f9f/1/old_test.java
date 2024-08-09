@Test
    public void testNextExecution2016() {
        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX));
        ExecutionTime executionTime = ExecutionTime.forCron(parser.parse("1 0 * * tue"));
        DateTime date = DateTime.parse("2016-05-24T01:02:50Z");
        assertEquals(DateTime.parse("2016-05-31T00:01:00Z"), executionTime.nextExecution(date));
    }