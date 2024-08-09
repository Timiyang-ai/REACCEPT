public final ScheduledTask schedule(final SchedulerTask task, final Quantum interval, boolean immediate) {
        boolean suspended = interval.equals(Quantum.SUSPENDED);
        if (immediate && !suspended) {
            immediateTaskService.submit(new Runnable() {
                public void run() {
                    task.onSchedule(System.currentTimeMillis());
                }
            });
        }

        DefaultScheduledTask scheduled = new DefaultScheduledTask(interval, task);

        return scheduled;
    }