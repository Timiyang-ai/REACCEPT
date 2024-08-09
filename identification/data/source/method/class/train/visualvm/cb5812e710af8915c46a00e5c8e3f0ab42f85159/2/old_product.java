public final void unschedule(final ScheduledTask task) {
        if (task.getInterval().equals(Quantum.SUSPENDED)) {
            return;
        }

        remove((DefaultScheduledTask) task, task.getInterval());
    }