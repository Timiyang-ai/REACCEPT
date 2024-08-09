@Override
    public void startScheduler() throws JobSchedulerException {
        try {
            getScheduler().start();
            clusterMessageSender.notifySchedulerResumed();
            triggerListeners(JobSchedulerEvent.schedulerStartedEvent());
        } catch (SchedulerException e) {
            throw new JobSchedulerException("Unable to Start the Scheduler", e);
        }
    }