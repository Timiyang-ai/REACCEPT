public static Timer createTimer(AbstractInstant instant, Procedure0 closure) {
        Logger logger = LoggerFactory.getLogger(ScriptExecution.class);
        JobKey jobKey = new JobKey(instant.toString() + ": " + closure.toString());
        Trigger trigger = newTrigger().startAt(instant.toDate()).build();
        Timer timer = new TimerImpl(jobKey, trigger.getKey(), instant);
        try {
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("procedure", closure);
            dataMap.put("timer", timer);
            JobDetail job = newJob(TimerExecutionJob.class).withIdentity(jobKey).usingJobData(dataMap).build();
            if (TimerImpl.scheduler.checkExists(job.getKey())) {
                TimerImpl.scheduler.deleteJob(job.getKey());
                logger.debug("Deleted existing Job {}", job.getKey().toString());
            }
            TimerImpl.scheduler.scheduleJob(job, trigger);
            logger.debug("Scheduled code for execution at {}", instant.toString());
            return timer;
        } catch (SchedulerException e) {
            logger.error("Failed to schedule code for execution.", e);
            return null;
        }
    }