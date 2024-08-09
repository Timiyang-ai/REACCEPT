@Test
    public void testNextExecution2014() {
        String crontab = "0 8 * * 1";//m,h,dom,m,dow ; every monday at 8AM
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX);
        CronParser parser = new CronParser(cronDefinition);
        Cron cron = parser.parse(crontab);
        ZonedDateTime date = ZonedDateTime.parse("2014-11-30T00:00:00Z");
        ExecutionTime executionTime = ExecutionTime.forCron(cron);
        assertEquals(ZonedDateTime.parse("2014-12-01T08:00:00Z"), executionTime.nextExecution(date).get());
    }