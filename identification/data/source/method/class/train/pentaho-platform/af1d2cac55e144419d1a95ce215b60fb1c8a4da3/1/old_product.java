@Override
  public BlockoutTrigger getBlockout(String blockoutName) throws SchedulerException {
    return (BlockoutTrigger) scheduler.getTrigger(blockoutName, BLOCK_GROUP);
  }