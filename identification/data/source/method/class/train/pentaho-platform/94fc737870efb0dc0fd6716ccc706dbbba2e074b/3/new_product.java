@Override
  public boolean shouldFireNow() throws SchedulerException {

    long currentTime = System.currentTimeMillis();
    for (IBlockoutTrigger blockOut : getBlockouts()) {

      if (!(blockOut instanceof Trigger)) {
        throw new SchedulerException(Messages.getInstance().getString(ERR_WRONG_BLOCKER_TYPE));
      }

      Trigger blockOutTrigger = (Trigger) blockOut;
      long lastFireTime = blockOutTrigger.getPreviousFireTime() != null ? blockOutTrigger.getPreviousFireTime()
          .getTime() : blockOutTrigger.getStartTime().getTime();
      long endLastFireTime = lastFireTime + blockOut.getBlockDuration();

      if (lastFireTime <= currentTime && currentTime <= endLastFireTime) {
        return false;
      }
    }

    return true;
  }