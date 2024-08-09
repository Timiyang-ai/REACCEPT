public final void unschedule(final ScheduledTask task) {
        remove((DefaultScheduledTask) task, task.getInterval());
        task.suspend();
    }