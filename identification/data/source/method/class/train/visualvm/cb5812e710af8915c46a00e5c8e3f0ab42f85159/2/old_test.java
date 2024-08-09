    @Test
    public void unschedule() {
        System.out.println("unschedule");
        final AtomicBoolean executed = new AtomicBoolean(false);
        SchedulerTask task = new SchedulerTask() {

            public void onSchedule(long timeStamp) {
                executed.set(true);
            }
        };
        Scheduler instance = Scheduler.sharedInstance();
        ScheduledTask scheduled = instance.schedule(task, Quantum.seconds(3), false);
        instance.unschedule(scheduled);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
        assertFalse(executed.get());
    }