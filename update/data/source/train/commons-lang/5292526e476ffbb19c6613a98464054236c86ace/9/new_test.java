@Test
    public void testInit() {
        final ScheduledExecutorService service = EasyMock
                .createMock(ScheduledExecutorService.class);
        EasyMock.replay(service);
        final TimedSemaphore semaphore = new TimedSemaphore(service, PERIOD, UNIT,
                LIMIT);
        EasyMock.verify(service);
        assertEquals("Wrong service", service, semaphore.getExecutorService());
        assertEquals("Wrong period", PERIOD, semaphore.getPeriod());
        assertEquals("Wrong unit", UNIT, semaphore.getUnit());
        assertEquals("Statistic available", 0, semaphore
                .getLastAcquiresPerPeriod());
        assertEquals("Average available", 0.0, semaphore
                .getAverageCallsPerPeriod(), .05);
        assertFalse("Already shutdown", semaphore.isShutdown());
        assertEquals("Wrong limit", LIMIT, semaphore.getLimit());
    }