    @Test
    public void startSchedulerTest() throws Exception {

        toTest.startScheduler();
        verify(scheduler).start();

        doThrow(new SchedulerException()).when(scheduler).start();
        try {
            toTest.startScheduler();
        } catch (final JobSchedulerException e) {
            return;
        }
        fail();
    }