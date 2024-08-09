@PluginFactory
    public static CronTriggeringPolicy createPolicy(
            @PluginConfiguration Configuration configuration,
            @PluginAttribute("schedule") final String schedule) {
        CronExpression cronExpression;
        if (schedule == null) {
            LOGGER.info("No schedule specified, defaulting to Daily");
            cronExpression = getSchedule(defaultSchedule);
        } else {
            cronExpression = getSchedule(schedule);
            if (cronExpression == null) {
                LOGGER.error("Invalid expression specified. Defaulting to Daily");
                cronExpression = getSchedule(defaultSchedule);
            }
        }
        return new CronTriggeringPolicy(cronExpression, configuration);
    }