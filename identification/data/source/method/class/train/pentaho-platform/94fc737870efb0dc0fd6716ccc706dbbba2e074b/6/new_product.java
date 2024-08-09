@Override
  public void addBlockout(IBlockoutTrigger blockout) throws SchedulerException {
    if (!(blockout instanceof Trigger)) {
      throw new SchedulerException(Messages.getInstance().getString(ERR_WRONG_BLOCKER_TYPE));
    }
    Trigger blockoutTrigger = (Trigger) blockout;
    JobDetail jd = new JobDetail(blockoutTrigger.getName(), BLOCK_GROUP, BlockoutJob.class);
    blockoutTrigger.setJobName(jd.getName());
    blockoutTrigger.setJobGroup(jd.getGroup());
    scheduler.scheduleJob(jd, blockoutTrigger);
  }