static @NonNull Scheduler guardedScheduler(@NonNull Scheduler scheduler) {
    return new GuardedScheduler(scheduler);
  }