    @Test
    public void pauseSchedulerTest() throws Exception {

        toTest.pauseScheduler();
        verify(scheduler).standby();

        doThrow(new SchedulerException()).when(scheduler).standby();
        try {
            toTest.pauseScheduler();
        } catch (final JobSchedulerException e) {
            return;
        }
        fail();
    }