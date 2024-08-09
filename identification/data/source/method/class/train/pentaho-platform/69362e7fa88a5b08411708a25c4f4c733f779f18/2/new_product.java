@Override
  public boolean shouldFireNow() throws SchedulerException {

    Date currentTime = new Date(System.currentTimeMillis());
    for (IBlockoutTrigger blockOut : getBlockouts()) {

      if (willBlockDate(blockOut, currentTime)) {
        return false;
      }
    }

    return true;
  }