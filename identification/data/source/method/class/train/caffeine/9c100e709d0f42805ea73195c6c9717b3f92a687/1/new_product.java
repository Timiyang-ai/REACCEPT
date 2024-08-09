static @NonNull Scheduler guardedScheduler(@NonNull Scheduler scheduler) {
    return (scheduler instanceof GuardedScheduler) ? scheduler : new GuardedScheduler(scheduler);
  }