public static boolean stop(final Context ctx, final String id) {
    // stop scheduled task
    final TimerTask task = ctx.jobs.tasks.get(id);
    if(task != null) task.cancel();
    // send stop signal to job
    final Job job = ctx.jobs.active.get(id);
    if(job != null) job.stop();
    // remove potentially cached result
    ctx.jobs.results.remove(id);

    return job != null || task != null;
  }