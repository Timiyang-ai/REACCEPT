    @Test
    public void ping() throws Exception {
        healthChecks.register("component1", new HealthCheck() {

            @Override
            protected Result check() throws Exception {
                return HealthCheck.Result.healthy();
            }
        });
        healthChecks.register("component2", new HealthCheck() {

            @Override
            protected Result check() throws Exception {
                return HealthCheck.Result.unhealthy("something went wrong");
            }
        });
        resouce.ping();
    }