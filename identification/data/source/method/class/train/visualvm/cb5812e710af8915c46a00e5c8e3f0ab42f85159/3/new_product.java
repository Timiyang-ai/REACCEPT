public final void unschedule(final ScheduledTask task) {
        if (task == null) return;
        remove((DefaultScheduledTask) task, task.getInterval());
        task.suspend();
    }