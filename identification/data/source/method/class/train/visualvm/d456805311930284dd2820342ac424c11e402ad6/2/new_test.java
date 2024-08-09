    @Test
    public void schedule() {
        System.out.println("schedule");
        final CountDownLatch barrier = new CountDownLatch(1);
        SchedulerTask task = new SchedulerTask() {

            public void onSchedule(long timeStamp) {
                barrier.countDown();
            }
        };
        Quantum interval = Quantum.seconds(5);
        Scheduler instance = Scheduler.sharedInstance();
        ScheduledTask scheduled = instance.schedule(task, interval, false);
        stasks.add(scheduled);
        try {
            boolean executed = barrier.await(8, TimeUnit.SECONDS);
            assertTrue(executed);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
    }