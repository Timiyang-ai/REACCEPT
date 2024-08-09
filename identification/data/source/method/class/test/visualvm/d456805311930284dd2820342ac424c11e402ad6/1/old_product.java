public final ScheduledTask schedule(final SchedulerTask task, final Quantum interval, boolean immediate) {
        if (interval.equals(Quantum.SUSPENDED)) {
            return ScheduledTask.SUSPENDED_TASK;
        }

        if (immediate) {
            task.onSchedule(System.currentTimeMillis());
        }

        DefaultScheduledTask scheduled = new DefaultScheduledTask(interval, task);
        add(scheduled, interval);

        return scheduled;
    }