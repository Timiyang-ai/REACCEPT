public void scheduleResult(final Job job) {
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        results.remove(job.job().id());
      }
    }, timeout);
  }