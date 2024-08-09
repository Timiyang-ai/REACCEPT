@Override
    public void pauseScheduler() throws JobSchedulerException {
        try {
            getScheduler().standby();
            clusterMessageSender.notifySchedulerPaused();
        } catch (SchedulerException e) {
            throw new JobSchedulerException("Unable to Pause the Scheduler", e);
        }
    }