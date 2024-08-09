@Override
  public boolean willFire(Trigger scheduleTrigger) throws SchedulerException {
    List<Date> fireTimes = getFireTimes(scheduleTrigger);

    for (IBlockoutTrigger blockOut : getBlockouts()) {

      // We must verify further if the schedule is blocked completely or if it will fire
      if (willBlockSchedule(scheduleTrigger, blockOut)) {

        Trigger blockOutTrigger = (Trigger) blockOut;

        // If recurrence intervals are the same, it will never fire
        if (!isComplexTrigger(blockOutTrigger) && !isComplexTrigger(scheduleTrigger)
            && getRecurrenceInterval(blockOutTrigger) == getRecurrenceInterval(scheduleTrigger)) {
          return false;
        }

        // Loop through fire times and verify whether block out is blocking the schedule completely
        boolean scheduleCompletelyBlocked = true;
        for (Date fireTime : fireTimes) {
          if (!(scheduleCompletelyBlocked = willBlockDate(blockOut, fireTime))) {
            break;
          }
        }

        // Return false if after n iterations
        if (scheduleCompletelyBlocked) {
          return false;
        }
      }
    }

    return true;
  }