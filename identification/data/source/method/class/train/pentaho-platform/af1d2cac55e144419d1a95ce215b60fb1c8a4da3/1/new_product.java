@Override
  public IBlockoutTrigger getBlockout(String blockoutName) throws SchedulerException {
    return (IBlockoutTrigger) scheduler.getTrigger(blockoutName, BLOCK_GROUP);
  }