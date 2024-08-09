    private CronTriggeringPolicy createPolicy() {
        return CronTriggeringPolicy.createPolicy(configuration, Boolean.TRUE.toString(), CRON_EXPRESSION);
    }