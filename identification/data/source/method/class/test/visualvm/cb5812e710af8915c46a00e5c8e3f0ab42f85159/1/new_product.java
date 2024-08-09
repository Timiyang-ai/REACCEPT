public final void unschedule(final ScheduledTask task) {
        if (task == null) return;
        task.suspend();
    }