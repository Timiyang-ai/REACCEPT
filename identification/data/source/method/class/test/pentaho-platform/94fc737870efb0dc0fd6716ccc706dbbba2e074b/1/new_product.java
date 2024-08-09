@Override
  public List<Trigger> willBlockSchedules(IBlockoutTrigger blockOut) throws SchedulerException {

    List<Trigger> blockedSchedules = new ArrayList<Trigger>();

    // Loop over trigger group names
    for (String groupName : this.scheduler.getTriggerGroupNames()) {

      // Skip block outs
      if (BLOCK_GROUP.equals(groupName)) {
        continue;
      }

      // Loop over job names within group
      for (String jobName : this.scheduler.getJobNames(groupName)) {
        Trigger schedule = this.scheduler.getTrigger(jobName, groupName);

        // Add schedule to list if block out conflicts at all
        if (willBlockSchedule(schedule, blockOut)) {
          blockedSchedules.add(schedule);
        }
      }
    }

    return blockedSchedules;
  }