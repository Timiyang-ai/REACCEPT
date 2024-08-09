@Override
  public void updateBlockout(String blockoutName, IBlockoutTrigger newBlockout) throws SchedulerException {
    if (!(newBlockout instanceof Trigger)) {
      throw new SchedulerException(Messages.getInstance().getString(ERR_WRONG_BLOCKER_TYPE));
    }
    Trigger newBlockoutTrigger = (Trigger) newBlockout;
    IBlockoutTrigger oldBlockout = null;
    try {
      oldBlockout = getBlockout(blockoutName);
      if (oldBlockout == null) {
        throw new SchedulerException(Messages.getInstance().getString(ERR_WRONG_BLOCKER_TYPE, blockoutName));
      }
    } catch (SchedulerException ex) {
      throw new SchedulerException(Messages.getInstance().getString(ERR_WRONG_BLOCKER_TYPE, blockoutName), ex);
    }

    deleteBlockout(blockoutName);
    Trigger oldBlockoutTrigger = (Trigger) oldBlockout;
    JobDetail jd = scheduler.getJobDetail(oldBlockoutTrigger.getJobName(), oldBlockoutTrigger.getJobGroup());

    newBlockoutTrigger.setJobName(jd.getName());
    newBlockoutTrigger.setJobGroup(jd.getGroup());
    scheduler.scheduleJob(jd, newBlockoutTrigger);
  }