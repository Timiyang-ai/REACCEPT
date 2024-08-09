@Override
  public boolean willFire(Trigger scheduleTrigger) throws SchedulerException {

    for (IBlockoutTrigger blockOut : getBlockouts()) {

      // We must verify further if the schedule is blocked completely or if it will fire
      if (willBlockSchedule(scheduleTrigger, blockOut)) {
        Trigger blockOutTrigger = (Trigger) blockOut;

        long blockOutRecurrence = getRecurrenceInterval(blockOutTrigger);
        long scheduleRecurrence = getRecurrenceInterval(scheduleTrigger);

        // If recurrences are the same, it will never fire
        if (blockOutRecurrence == scheduleRecurrence) {
          return false;
        }

        /*
         * Build list of next n occurrences for block out and schedule and do O(n^2) comparisons 
         */

        int n = 100;
        long[] blockOuts = new long[n];
        long[] schedules = new long[n];
        for (int i = 0; i < n; i++) {
          blockOuts[i] = blockOutTrigger.getNextFireTime().getTime() + blockOutRecurrence * i;
          schedules[i] = scheduleTrigger.getNextFireTime().getTime() + scheduleRecurrence * i;
        }

        long blockOutEndTime = blockOutTrigger.getEndTime() == null ? -1 : blockOutTrigger.getEndTime().getTime();
        long scheduleEndTime = scheduleTrigger.getEndTime() == null ? -1 : scheduleTrigger.getEndTime().getTime();
        boolean scheduleCompletelyBlocked = true;
        for (int i = 0; i < n; i++) {
          long scheduleStart = schedules[i];

          // Out of range of schedule
          if (!scheduleCompletelyBlocked || (scheduleEndTime > 0 && scheduleStart >= scheduleEndTime)) {
            break;
          }

          // Loop over schedule dates
          for (int j = 0; j < n; j++) {
            long blockOutRangeStart = blockOuts[j];
            long blockOutRangeEnd = blockOutRangeStart + blockOut.getBlockDuration();

            // Out of range of block out
            if (blockOutRangeStart > scheduleStart || (blockOutEndTime > 0 && blockOutRangeStart >= blockOutEndTime)) {
              break;
            }

            // Ensures that the schedule start is within block out range
            scheduleCompletelyBlocked = blockOutRangeStart <= scheduleStart && scheduleStart <= blockOutRangeEnd;

            if (!scheduleCompletelyBlocked) {
              break;
            }
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